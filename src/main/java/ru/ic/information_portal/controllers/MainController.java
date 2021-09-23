package ru.ic.information_portal.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.ic.information_portal.entity.*;
import ru.ic.information_portal.reports.FormRequest;
import ru.ic.information_portal.reports.ResponseFactory;
import ru.ic.information_portal.repositories.*;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * В данном классе реализованы методы взаимодействия с сущностями проекта для взаимодействия с веб-формами интерфейса
 */

@Controller
public class MainController {

    final SrnRepository srnRepository;
    final DepartmentsRepository departmentRepository;
    final UsersRepository usersRepository;
    final ShortcomingRepository shortcomingRepository;
    final DevicesRepository devicesRepository;
    final MeasuresRepository measuresRepository;
    final StatusRepository statusRepository;
    final JournalRepository journalRepository;
    final RoadCategoryRepository roadCategoryRepository;
    final SFixTermRepository sFixTermRepository;
    final RelatedFilesRepository relatedFilesRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public MainController(SrnRepository srnRepository,
                          DepartmentsRepository departmentRepository,
                          UsersRepository usersRepository,
                          ShortcomingRepository shortcomingRepository,
                          DevicesRepository devicesRepository,
                          MeasuresRepository measuresRepository,
                          StatusRepository statusRepository,
                          JournalRepository journalRepository,
                          RoadCategoryRepository roadCategoryRepository,
                          SFixTermRepository sFixTermRepository,
                          RelatedFilesRepository relatedFilesRepository) {
        this.srnRepository = srnRepository;
        this.departmentRepository = departmentRepository;
        this.usersRepository = usersRepository;
        this.shortcomingRepository = shortcomingRepository;
        this.devicesRepository = devicesRepository;
        this.measuresRepository = measuresRepository;
        this.statusRepository = statusRepository;
        this.journalRepository = journalRepository;
        this.roadCategoryRepository = roadCategoryRepository;
        this.sFixTermRepository = sFixTermRepository;
        this.relatedFilesRepository = relatedFilesRepository;
    }

    /**
     * Метод контроллера страницы manager, в котором реализовано получение справочников
     * в виде массивов классов сущностей для работы с записью об инциденте, и передача
     * справочников в аттрибуты страницы
     *
     * @param model интерфейс определяющий аттрибуты
     * @return возвращяет путь к странице
     */

    @GetMapping(path = "/manager")
    public String manager(Model model) {


        User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = usersRepository.findByUsername(userAuth.getUsername());
        getVocabulary(model, user);
        model.addAttribute("user", user);

        return "manager";
    }

    /**
     * Метод контроллера реализующие получение справочников и записи об инциденте
     * по идентификационному номеру и дальнейшая передача в аттрибуты страницы
     *
     * @param id    идентификационный номер записи об инциденте
     * @param model интерфейс определяющий аттрибуты
     * @return возвращяет путь к странице
     */

    @GetMapping(path = "/manager/get")
    public String getSrn(@RequestParam(defaultValue = "0") int id, Model model) {

        model.addAttribute("data", getData(id));

        User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = usersRepository.findByUsername(userAuth.getUsername());
        model.addAttribute("user", user);
        getVocabulary(model, user);
        try {
            StreetRoadNetwork srn = srnRepository.findById(id);
            if ((user.getDepartment().getCode() == 1140000) || srn.getDepartment().getCode() == user.getDepartment().getCode()) {
                model.addAttribute("srn", srn);
                model.addAttribute("journal", journalRepository.findAllBySrn_IdOrderByEntryDate(id));
            }

        } catch (NumberFormatException | NullPointerException error) {
            model.addAttribute("errors", error.getMessage());
        }

        return "manager";
    }

    /**
     * Метод контроллера реализующий добавление записи об инциденте из веб-формы
     * в базу данных, возвращает сформированную запись из базы данных и записывает
     * в аттрибуты для отображения в веб-форме
     * <p>
     * //     * @param incidentForm сведения об инциденте переданные с веб-формы
     *
     * @param model интерфейс определяющий аттрибуты
     * @return возвращяет путь к странице
     */

    @PostMapping(path = "/manager/add")
    public String addSrn(StreetRoadNetwork srn, BindingResult bindingResult, Model model) {
        model.addAttribute("data", getData(srn.getId()));

        User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = usersRepository.findByUsername(userAuth.getUsername());
        model.addAttribute("user", user);
        getVocabulary(model, user);

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
        }

        try {
            if (srn.getId() == 0 && srn.getCreateDate() == null) {
                srn.setCreateDate(new Date(new java.util.Date().getTime()));
            } else srn.setCreateDate(srnRepository.findById(srn.getId()).getCreateDate());

            if (srn.getFoundDate() == null) {
                if (srn.getId() == 0) {
                    srn.setFoundDate(new Date(new java.util.Date().getTime()));
                } else {
                    srn.setFoundDate(srnRepository.findById(srn.getId()).getFoundDate());
                }
            }

            if (srn.getStatus() == null) {
                if (srn.getId() == 0) {
                    srn.setStatus(this.statusRepository.findById(1));
                } else {
                    srn.setStatus(this.srnRepository.findById(srn.getId()).getStatus());
                }
            }

            if (srn.getReferralDate() != null && !srn.getStatus().isFixed()) {
                srn.setStatus(this.statusRepository.findById(2));
            }

            if (srn.getStatus().isFixed()) {
                srn.setCloseDate(new Date(new java.util.Date().getTime()));
            }

            if (srn.getDepartment() == null) {
                srn.setDepartment(user.getDepartment());
            }
            if (srn.getControl() != null) {
                if (!srn.getControl().equals("")) {
                    srn.setController(user.getName());
                    srn.setUnderControl(true);
                } else {
                    srn.setController("");
                    srn.setUnderControl(false);
                }
            }

            if (srn.getSendTo().contains("\"")) {
                srn.setSendTo(srn.getSendTo().replaceAll("\"", ""));
            }

            model.addAttribute("srn", srnRepository.save(srn));
            journaling(srn);
            model.addAttribute("journal", journalRepository.findAllBySrn_IdOrderByEntryDate(srn.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "manager";
    }

    @PostMapping(path = "/upload_shortcoming")
    public String uploadFileShortcoming(@RequestParam("file") MultipartFile file,
                                        StreetRoadNetwork srn,
                                        Model model) throws IOException {

        if (file != null) {
            String fileName = srn.getId() + ".1." + new Timestamp(new Date(new java.util.Date().getTime()).getTime()) + "." + file.getOriginalFilename();
            File uploadFile = new File(uploadPath + "/" + fileName);
            file.transferTo(uploadFile);

//            imgCompressor(uploadFile);

            RelatedFiles relatedFiles = new RelatedFiles();
            relatedFiles.setSrn(srn.getId());
            relatedFiles.setType(1);
            relatedFiles.setFileName(fileName);
            relatedFiles.setLoadDate(new Timestamp(new Date(new java.util.Date().getTime()).getTime()));
            User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Users user = usersRepository.findByUsername(userAuth.getUsername());
            relatedFiles.setLoadUserName(user.getUsername());

            relatedFilesRepository.save(relatedFiles);
            model.addAttribute("srn", srnRepository.findById(srn.getId()));
            model.addAttribute("data", getData(srn.getId()));
        }

        return "manager";
    }

    @PostMapping(path = "/upload_doc")
    public String uploadFileDoc(@RequestParam("file") MultipartFile file,
                                StreetRoadNetwork srn,
                                Model model) throws IOException {

        if (file != null) {
            String fileName = srn.getId() + ".2." + new Timestamp(new Date(new java.util.Date().getTime()).getTime()) + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + fileName));

            RelatedFiles relatedFiles = new RelatedFiles();
            relatedFiles.setSrn(srn.getId());
            relatedFiles.setType(2);
            relatedFiles.setFileName(fileName);
            relatedFiles.setLoadDate(new Timestamp(new Date(new java.util.Date().getTime()).getTime()));
            User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Users user = usersRepository.findByUsername(userAuth.getUsername());
            relatedFiles.setLoadUserName(user.getUsername());

            relatedFilesRepository.save(relatedFiles);
            model.addAttribute("srn", srnRepository.findById(srn.getId()));
            model.addAttribute("data", getData(srn.getId()));
        }

        return "manager";
    }

    @PostMapping(path = "/upload_fix")
    public String uploadFileFix(@RequestParam("file") MultipartFile file,
                                StreetRoadNetwork srn,
                                Model model) throws IOException {

        if (file != null) {
            String fileName = srn.getId() + ".3." + new Timestamp(new Date(new java.util.Date().getTime()).getTime()) + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + fileName));

            RelatedFiles relatedFiles = new RelatedFiles();
            relatedFiles.setSrn(srn.getId());
            relatedFiles.setType(3);
            relatedFiles.setFileName(fileName);
            relatedFiles.setLoadDate(new Timestamp(new Date(new java.util.Date().getTime()).getTime()));
            User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Users user = usersRepository.findByUsername(userAuth.getUsername());
            relatedFiles.setLoadUserName(user.getUsername());

            relatedFilesRepository.save(relatedFiles);
            model.addAttribute("srn", srnRepository.findById(srn.getId()));
            model.addAttribute("data", getData(srn.getId()));
        }

        return "manager";
    }

    //    /**
//     * Метод контроллера реализующий обновление записи об инциденте из веб-формы в баз данных, возвращает обновленную
//     * запись из базы данных и записывает в аттрибуты для отображения в веб-форме
//     *
//     * @param incidentForm сведения об инциденте переданные с веб-формы
//     * @param id           идентификатор записи об инциденте
//     * @param model        интерфейс определяющий аттрибуты
//     * @return возвращяет путь к странице
//     */
//
//    /**
//     * Метод контроллера реализующий обновление в баз данных статуса записи об инциденте
//     * на "Решен" и формирование даты решения заявки, возвращает обновленную
//     * запись из базы данных и записывает в аттрибуты для отображения в веб-форме
//     *
//     * @param id    идентификатор записи об инциденте
//     * @param model интерфейс определяющий аттрибуты
//     * @return возвращяет путь к странице
//     */
//
    @PostMapping(path = "/manager/fix")
    public String fixSrn(@RequestParam int id, Model model) {
        model.addAttribute("data", getData(id));

        User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = usersRepository.findByUsername(userAuth.getUsername());
        model.addAttribute("user", user);
        getVocabulary(model, user);

        try {
            StreetRoadNetwork srn = srnRepository.findById(id);
            srn.setStatus(statusRepository.findById(3));
            srn.setCloseDate(new Date(new java.util.Date().getTime()));
            model.addAttribute("srn", srnRepository.save(srn));
            journaling(srn);
            model.addAttribute("journal", journalRepository.findAllBySrn_IdOrderByEntryDate(srn.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "manager";
    }

    /**
     * Метод контроллера страницы dashboard, в котором реализовано получение записей об инциденте,
     * имеющих статус отличающийся от статуса "Решен", и их передача в аттрибуты страницы
     *
     * @param model интерфейс определяющий аттрибуты
     * @return возвращяет путь к странице
     */

    @GetMapping(path = "/dashboard")
    public String dashboard(@RequestParam(defaultValue = "all") String filter, Model model) {

        User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = usersRepository.findByUsername(userAuth.getUsername());
        model.addAttribute("user", user);
        getVocabulary(model, user);

        int depCode = 0;
        if (user != null) {
            depCode = user.getDepartment().getCode();
        }
        Iterable<StreetRoadNetwork> allSrnByDepCode;
        switch (filter) {
            case "inWork":
                if (depCode == 1140000) {
                    allSrnByDepCode = srnRepository.findAllByStatus_FixedOrderById(false);
                } else {
                    allSrnByDepCode = srnRepository.findAllByDepartment_CodeAndStatus_FixedOrderById(
                            depCode,
                            false
                    );
                }
                model.addAttribute("fastFilterRadio", "inWork");
                break;
            case "underControl":
                if (depCode == 1140000) {
                    allSrnByDepCode = srnRepository.findAllByUnderControlOrderById(true);
                } else {
                    allSrnByDepCode = srnRepository.findAllByDepartment_CodeAndUnderControlOrderById(depCode, true);
                }
                model.addAttribute("fastFilterRadio", "underControl");
                break;
            default:
                if (depCode == 1140000) {
                    allSrnByDepCode = srnRepository.findAllByCreateDateAfterOrderById(
                            new Date(new java.util.Date().getTime() - (30L * 24 * 60 * 60 * 1000))
                    );
                } else {
                    allSrnByDepCode = srnRepository.findAllByDepartment_CodeAndCreateDateAfterOrderById(
                            depCode,
                            new Date(new java.util.Date().getTime() - (30L * 24 * 60 * 60 * 1000))
                    );
                }
                model.addAttribute("fastFilterRadio", "all");
                break;
        }
        srnToDashboard(model, allSrnByDepCode);

        return "dashboard";
    }

    @PostMapping(path = "/dashboard/filter")
    public String dashboardFilter(FormRequest formRequest, BindingResult bindingResult, Model model) {

        User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = usersRepository.findByUsername(userAuth.getUsername());
        model.addAttribute("user", user);
        getVocabulary(model, user);

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
        }

//        model.addAttribute("formRequest", new FormRequest());
//        int depCode = 0;
//        if (user != null) {
//            depCode = user.getDepartment().getCode();
//        }
        ArrayList<StreetRoadNetwork> allSrnByFilter = new ArrayList<>();

        if (user.getDepartment().getCode() != 1140000) {
            formRequest.setDepartment(user.getDepartment());
        }

        if (formRequest.getDepartment() == null) {
            for (StreetRoadNetwork srn : srnRepository.findAll()) {
                allSrnByFilter.add(srn);
            }
        } else {
            for (StreetRoadNetwork srn : srnRepository.findAllByDepartment_CodeOrderById(formRequest.getDepartment().getCode())) {
                allSrnByFilter.add(srn);
            }
        }

        if (formRequest.getFoundPeriodStart() != null) {
            ArrayList<StreetRoadNetwork> list = new ArrayList<>();
            for (StreetRoadNetwork srn : allSrnByFilter) {
                if (srn.getCreateDate().getTime() >= formRequest.getFoundPeriodStart().getTime()) {
                    list.add(srn);
                }
            }
            allSrnByFilter = list;
        }

        if (formRequest.getFoundPeriodEnd() != null) {
            ArrayList<StreetRoadNetwork> list = new ArrayList<>();
            for (StreetRoadNetwork srn : allSrnByFilter) {
                if (srn.getCreateDate().getTime() <= formRequest.getFoundPeriodEnd().getTime()) {
                    list.add(srn);
                }
            }
            allSrnByFilter = list;
        }

        model.addAttribute("formRequest", formRequest);
        srnToDashboard(model, allSrnByFilter);
        return "dashboard";
    }

    private void srnToDashboard(Model model, Iterable<StreetRoadNetwork> allSrn) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (StreetRoadNetwork srn : allSrn) {
            String color = "";
            if (!srn.getStatus().isFixed() && srn.getReferralDate() != null) {
                if (new java.util.Date().getTime()
                        - srn.getReferralDate().getTime()
                        > (7 * 24 * 60 * 60 * 1000)) {
                    color = "class=\"alert-danger\"";
                } else if (new java.util.Date().getTime()
                        - srn.getReferralDate().getTime()
                        > (5 * 24 * 60 * 60 * 1000)) {
                    color = "class=\"alert-warning\"";
                }
            }


            String notes = "";

            try {
                if (srn.getControl() != null && !srn.getControl().equals("")) {
                    notes = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"32\" height=\"32\" fill=\"currentColor\"" +
                            " style=\"color: red\" class=\"bi bi-exclamation-circle-fill text-alert\" viewBox=\"0 0 16 16\">\n" +
                            " <title>Контроль: " + srn.getControl() + ". " + srn.getController() + "</title> <path d=\"M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8 4a.905.905 0 0 0-.9.995l.35 3.507a.552.552 0 0 0 1.1 0l.35-3.507A.905.905 0 0 0 8 4zm.002 6a1 1 0 1 0 0 2 1 1 0 0 0 0-2z\"/>" +
                            "\n</svg>";
                }

                if (!srn.getStatus().isFixed() && srn.getMeasures() != null && srn.getMeasures().getTitle().equals("Протокол")) {
                    notes += " <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"32\" height=\"32\" fill=\"currentColor\" class=\"bi bi-briefcase\" viewBox=\"0 0 16 16\">\n" +
                            "  <title>Административный материал</title><path d=\"M6.5 1A1.5 1.5 0 0 0 5 2.5V3H1.5A1.5 1.5 0 0 0 0 4.5v8A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-8A1.5 1.5 0 0 0 14.5 3H11v-.5A1.5 1.5 0 0 0 9.5 1h-3zm0 1h3a.5.5 0 0 1 .5.5V3H6v-.5a.5.5 0 0 1 .5-.5zm1.886 6.914L15 7.151V12.5a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5V7.15l6.614 1.764a1.5 1.5 0 0 0 .772 0zM1.5 4h13a.5.5 0 0 1 .5.5v1.616L8.129 7.948a.5.5 0 0 1-.258 0L1 6.116V4.5a.5.5 0 0 1 .5-.5z\"/>\n" +
                            "</svg>";
                }
            } catch (
                    NullPointerException e) {
                e.printStackTrace();
            }

//            String miniature = "";
//
//            try {
//                if (relatedFilesRepository.findFirstBySrnAndType(srn.getId(), 1) != null) {
//                    miniature = "\"<img src=\"/srnFiles/uploads/miniature/"
//                            + relatedFilesRepository.findFirstBySrnAndType(srn.getId(), 1).getFileName()
//                            + "\" width=\"100%\">";
//                }
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//            }

            stringBuilder.append("<tr ")
                    .append(color)
                    .append("onclick=\"location.href='/srn/manager/get?id=")
                    .append(srn.getId())
                    .append("'\"")
                    .append("><td class=\"text-center\">")
                    .append(srn.getId())
//                    .append("</td><td>")
//                    .append(miniature)
                    .append("</td><td>")
                    .append(srn.getCreateDate())
                    .append("</td><td>")
                    .append(srn.getDepartment().getTitle())
                    .append("</td><td>")
                    .append(srn.getFoundWho())
                    .append("</td><td>")
                    .append(srn.getFoundPlace())
                    .append("</td><td class=\"text-center\">")
                    .append(srn.getFoundDate())
                    .append("</td><td>")
                    .append(srn.getShortcoming().getShortTitle())
                    .append("</td><td class=\"text-center\">")
//                    .append(srn.getComment())
//                    .append("</td><td>")
//                    .append(srn.getDevices().getTitle())
//                    .append("</td><td>")
//                    .append(srn.getMeasures().getTitle())
//                    .append("</td><td>")
//                    .append(srn.getSendTo())
//                    .append("</td><td>")
//                    .append(srn.getMeasuresDate())
                    .append(srn.getStatus().getTitle())
                    .append("</td><td class=\"text-center\">")
                    .append(notes)
                    .append("</td></tr>");

            count++;
        }

        String footer = "Всего выбрано материалов: " + count + ".";
        model.addAttribute("srnAllsb", stringBuilder.toString());
        model.addAttribute("footer", footer);
    }

    /**
     * Локальный метод формирования аттрибутов справочников
     *
     * @param model интерфейс определяющий аттрибуты
     */

    @GetMapping(path = "/reports")
    public String reports(/*FormRequest formRequest,*/ Model model) {

        User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = usersRepository.findByUsername(userAuth.getUsername());
        model.addAttribute("user", user);
        getVocabulary(model, user);

        ResponseFactory res = new ResponseFactory(srnRepository, departmentRepository);
        model.addAttribute("report", res.toHTML(res.generateBaseReport()));

        return "reports";
    }

    private void getVocabulary(Model model, Users user) {

        StringBuilder sb = new StringBuilder();
        for (Shortcoming shortcoming : shortcomingRepository.findAll()) {
            if (shortcoming.getShortTitle() != null) {
                sb.append("<option value=\"")
                        .append(shortcoming.getId())
                        .append("\">")
                        .append(shortcoming.getShortTitle())
                        .append("</option>");
            }
        }
        String shortcomings = sb.toString();
        model.addAttribute("shortcomings", shortcomings);

        sb = new StringBuilder();
        for (Department department : departmentRepository.findAll()) {
            if (department.getCode() != 1140000) {
                sb.append("<option value=\"")
                        .append(department.getId())
                        .append("\">")
                        .append(department.getTitle())
                        .append("</option>");
            }
        }
        String departments = sb.toString();
        model.addAttribute("departments", departments);

        sb = new StringBuilder();
        for (Devices devices : devicesRepository.findAll()) {
            sb.append("<option value=\"")
                    .append(devices.getId())
                    .append("\">")
                    .append(devices.getTitle())
                    .append("</option>");
        }
        String devices = sb.toString();
        model.addAttribute("devices", devices);

        sb = new StringBuilder();
        for (Measures measures : measuresRepository.findAll()) {
            sb.append("<option value=\"")
                    .append(measures.getId())
                    .append("\">")
                    .append(measures.getTitle())
                    .append("</option>");
        }
        String measure = sb.toString();
        model.addAttribute("measure", measure);

        sb = new StringBuilder();
        for (Status status : statusRepository.findAll()) {
            sb.append("<option value=\"")
                    .append(status.getId())
                    .append("\">")
                    .append(status.getTitle())
                    .append("</option>");
        }
        String status = sb.toString();
        model.addAttribute("status", status);


        sb = new StringBuilder();
        for (RoadCategory roadCategory : roadCategoryRepository.findAll()) {
            sb.append("<option value=\"")
                    .append(roadCategory.getId())
                    .append("\">")
                    .append(roadCategory.getTitle())
                    .append("</option>");
        }
        String roadCategory = sb.toString();
        model.addAttribute("roadCategories", roadCategory);

        if (user.getDepartment().getCode() != 1140000) {
            model.addAttribute("form_disable", "disabled");
            model.addAttribute("form_hidden", "hidden");
        }
    }

    private String getData(int srn_id) {
        StringBuilder sb = new StringBuilder();
        sb.append("<label>Недостаток</label>");

        for (RelatedFiles a : relatedFilesRepository.findAllBySrnAndTypeOrderById(srn_id, 1)) {
            sb.append("<img src=\"/srnFiles/uploads/")
                    .append(a.getFileName())
                    .append("\" width=\"100%\">");
        }
        sb.append("<hr class=\"mb\"><label>Документы</label>");

        for (RelatedFiles a : relatedFilesRepository.findAllBySrnAndTypeOrderById(srn_id, 2)) {
            if (a.getFileName().endsWith("pdf")) {
                sb.append("<object data=\"/srnFiles/uploads/")
                        .append(a.getFileName())
                        .append("\" type=\"application/pdf\" width=\"100%\" height=\"165%\"></object>");
            } else {
                sb.append("<img src=\"/srnFiles/uploads/")
                        .append(a.getFileName())
                        .append("\" width=\"100%\">");
            }
        }
        sb.append("<hr class=\"mb\"><label>Результат</label>");

        for (RelatedFiles a : relatedFilesRepository.findAllBySrnAndTypeOrderById(srn_id, 3)) {
            sb.append("<img src=\"/srnFiles/uploads/")
                    .append(a.getFileName())
                    .append("\" width=\"100%\">");
        }
        return sb.toString();
    }

//    public void imgCompressor(File file) throws IOException {
//        BufferedImage image = ImageIO.read(file);
//        File compressedFile = new File(uploadPath + "/miniature/" + file.getName());
//        OutputStream outputStream = new FileOutputStream(compressedFile);
//        Iterator<ImageWriter> imageWriterIterator = ImageIO.getImageWritersByFormatName("jpg");
//        ImageWriter imageWriter = (ImageWriter) imageWriterIterator.next();
//        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
//        imageWriter.setOutput(imageOutputStream);
//        ImageWriteParam param = imageWriter.getDefaultWriteParam();
//        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//        param.setCompressionQuality(0.05f);
//        imageWriter.write(null, new IIOImage(
//                image, null, null
//        ), param);
//        outputStream.close();
//        imageOutputStream.close();
//        imageWriter.dispose();
//    }

    public void journaling(StreetRoadNetwork srn) throws IOException {
        Journal journal = new Journal();
        journal.setSrn(srn);
        ObjectMapper objectMapper = new ObjectMapper();
        journal.setSrnJson(objectMapper.writeValueAsString(srn));
        journal.setEntryDate(new Timestamp(new java.util.Date().getTime()));

        journalRepository.save(journal);
    }
}