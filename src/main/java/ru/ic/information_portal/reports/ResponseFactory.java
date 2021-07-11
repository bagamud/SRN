package ru.ic.information_portal.reports;

import ru.ic.information_portal.entity.Department;
import ru.ic.information_portal.repositories.DepartmentsRepository;
import ru.ic.information_portal.repositories.SrnRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResponseFactory {
    final SrnRepository srnRepository;
    final DepartmentsRepository departmentRepository;
//    FormRequest formRequest;

    public ResponseFactory(SrnRepository srnRepository, DepartmentsRepository departmentRepository) {
        this.srnRepository = srnRepository;
        this.departmentRepository = departmentRepository;
    }

    public ArrayList<String[]> generateBaseReport() {
        ArrayList<String[]> report = new ArrayList<>();
        report.add(new String[]{"Наименование", "Всего карточек", "Исправлено", "Неисправлено"});


        for (Department dep : departmentRepository.findAll()) {
            report.add(new String[]{
                    dep.getTitle(),
                    String.valueOf(srnRepository.countByDepartment_code(dep.getCode())),
                    String.valueOf(srnRepository.countByDepartment_codeAndResult_Fixed(dep.getCode(), true)),
                    String.valueOf(srnRepository.countByDepartment_codeAndResult_Fixed(dep.getCode(), false))
            });
        }

        report.add(new String[]{
                "Санкт-Петербург",
                String.valueOf(srnRepository.countByDepartment_RegCode(1140)),
                String.valueOf(srnRepository.countByDepartment_RegCodeAndResult_Fixed(1140, true)),
                String.valueOf(srnRepository.countByDepartment_RegCodeAndResult_Fixed(1140, false))
        });
        report.add(new String[]{
                "Ленинградская область",
                String.valueOf(srnRepository.countByDepartment_RegCode(1141)),
                String.valueOf(srnRepository.countByDepartment_RegCodeAndResult_Fixed(1141, true)),
                String.valueOf(srnRepository.countByDepartment_RegCodeAndResult_Fixed(1141, false))
        });
        report.add(new String[]{
                "Всего",
                String.valueOf(srnRepository.count()),
                String.valueOf(srnRepository.countByResult_Fixed(true)),
                String.valueOf(srnRepository.countByResult_Fixed(false))
        });
        return report;
    }

//    public String generateReport(FormRequest formRequest) {
//        ArrayList<String[]> report = new ArrayList<>();
//        report.put("Всего по Санкт-Петербургу и Ленинградской области", srnRepository.countAll());
//        return toHTML(report);
//    }

    public String toHTML(ArrayList<String[]> report) {
        StringBuilder sb = new StringBuilder();

        sb.append("<table class=\"table\">");
        for (String[] strings : report) {
            sb.append("<tr>")
                    .append("<td>")
                    .append(strings[0])
                    .append("</td><td>")
                    .append(strings[1])
                    .append("</td><td>")
                    .append(strings[2])
                    .append("</td><td>")
                    .append(strings[3])
                    .append("</td>")
                    .append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
}
