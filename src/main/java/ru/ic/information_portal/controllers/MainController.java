package ru.ic.information_portal.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ic.information_portal.entity.*;
import ru.ic.information_portal.repositories.*;

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
    final ResultRepository resultRepository;
    final JournalRepository journalRepository;

    public MainController(SrnRepository srnRepository,
                          DepartmentsRepository departmentRepository,
                          UsersRepository usersRepository,
                          ShortcomingRepository shortcomingRepository,
                          DevicesRepository devicesRepository,
                          MeasuresRepository measuresRepository,
                          ResultRepository resultRepository, JournalRepository journalRepository) {
        this.srnRepository = srnRepository;
        this.departmentRepository = departmentRepository;
        this.usersRepository = usersRepository;
        this.shortcomingRepository = shortcomingRepository;
        this.devicesRepository = devicesRepository;
        this.measuresRepository = measuresRepository;
        this.resultRepository = resultRepository;
        this.journalRepository = journalRepository;
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
        getVocabulary(model);
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
    public String getSrn(@RequestParam int id, Model model) {
        getVocabulary(model);

        try {
            StreetRoadNetwork srn = srnRepository.findById(id);
            model.addAttribute("srn", srn);
            model.addAttribute("journal", journalRepository.findAllBySrn_IdOrderByEntryDate(id));
        } catch (NumberFormatException | NullPointerException error) {
            model.addAttribute("error", error.getMessage());
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
        getVocabulary(model);

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
        }

        try {
            srn.setCreateDate(new Date(new java.util.Date().getTime()));
            if (srn.getResult() == null) {
                srn.setResult(resultRepository.findById(0));
            }
            model.addAttribute("srn", srnRepository.save(srn));
            journaling(srn);
            model.addAttribute("journal", journalRepository.findAllBySrn_IdOrderByEntryDate(srn.getId()));
        } catch (Exception e) {
            e.printStackTrace();
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
//    @PostMapping(path = "/manager/upd")
//    public String updateIncident(/*@Valid*/ StreetRoadNetwork srn, @RequestParam int id, Model model) {
//        getVocabulary(model);
//        model.addAttribute("srn", srnRepository.updateIncident(incident, id)));
//        return "manager";
//    }

    /**
     * Метод контроллера реализующий обновление в баз данных статуса записи об инциденте
     * на "Решен" и формирование даты решения заявки, возвращает обновленную
     * запись из базы данных и записывает в аттрибуты для отображения в веб-форме
     *
     * @param id    идентификатор записи об инциденте
     * @param model интерфейс определяющий аттрибуты
     * @return возвращяет путь к странице
     */

    @PostMapping(path = "/manager/fix")
    public String fixSrn(@RequestParam int id, Model model) {
        getVocabulary(model);

        StreetRoadNetwork srn = srnRepository.findById(id);

        srn.setResult(resultRepository.findById(1));
        srn.setCloseDate(new Date(new java.util.Date().getTime()));

        try {
            model.addAttribute("srn", srnRepository.save(srn));
            journaling(srn);
            model.addAttribute("journal", journalRepository.findAllBySrn_IdOrderByEntryDate(id));
        } catch (IOException e) {
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
    public String dashboard(Model model) {
        getVocabulary(model);

        ArrayList<Users> users = (ArrayList<Users>) model.getAttribute("users");
        int depCode = 1140000;
        if (users != null) {
            for (Users user : users) {
                depCode = user.getDepartment().getCode();
            }
        }
        Iterable<StreetRoadNetwork> allSrnByDepCode;
        if (depCode == 1140000) allSrnByDepCode = srnRepository.findAll();
        else {
            allSrnByDepCode = srnRepository.findAllByDepartment_CodeOrderById(depCode);
        }
        StringBuilder stringBuilder = new StringBuilder();


        for (StreetRoadNetwork srn : allSrnByDepCode) {
            stringBuilder.append("<tr onclick=\"location.href='/manager/get?id=")
                    .append(srn.getId())
                    .append("'\"")
                    .append("><td>")
                    .append(srn.getId())
                    .append("</td><td>")
                    .append(srn.getDepartment().getTitle())
                    .append("</td><td>")
                    .append(srn.getFoundWho())
                    .append("</td><td>")
                    .append(srn.getFoundPlace())
                    .append("</td><td>")
                    .append(srn.getFoundDate())
                    .append("</td><td>")
                    .append(srn.getShortcoming().getTitle())
                    .append("</td><td>")
                    .append(srn.getComment())
                    .append("</td><td>")
                    .append(srn.getDevices().getTitle())
                    .append("</td><td>")
                    .append(srn.getMeasures().getTitle())
                    .append("</td><td>")
                    .append(srn.getSendTo())
                    .append("</td><td>")
                    .append(srn.getMeasuresDate())
                    .append("</td><td>")
                    .append(srn.getResult().getTitle())
                    .append("</td></tr>");
        }
        model.addAttribute("srnAllsb", stringBuilder.toString());


        return "dashboard";
    }

    /**
     * Локальный метод формирования аттрибутов справочников
     *
     * @param model интерфейс определяющий аттрибуты
     */

    private void getVocabulary(Model model) {
        StringBuilder sb = new StringBuilder();
        for (Shortcoming shortcoming : shortcomingRepository.findAll()) {
            sb.append("<option value=\"")
                    .append(shortcoming.getId())
                    .append("\">")
                    .append(shortcoming.getTitle())
                    .append("</option>");
        }
        String shortcomings = sb.toString();
        model.addAttribute("shortcomings", shortcomings);

        sb = new StringBuilder();
        for (Department department : departmentRepository.findAll()) {
            sb.append("<option value=\"")
                    .append(department.getId())
                    .append("\">")
                    .append(department.getTitle())
                    .append("</option>");
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

        model.addAttribute("users", usersRepository.findAll());

        sb = new StringBuilder();
        for (Result result : resultRepository.findAll()) {
            sb.append("<option value=\"")
                    .append(result.getId())
                    .append("\">")
                    .append(result.getTitle())
                    .append("</option>");
        }
        String result = sb.toString();
        model.addAttribute("result", result);
    }


    public void journaling(StreetRoadNetwork srn) throws IOException {
        Journal journal = new Journal();
        journal.setSrn(srn);
        ObjectMapper objectMapper = new ObjectMapper();
        journal.setSrnJson(objectMapper.writeValueAsString(srn));
        journal.setEntryDate(new Timestamp(new java.util.Date().getTime()));

        journalRepository.save(journal);
    }
}
