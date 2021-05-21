package ru.ic.information_portal.reports;

import ru.ic.information_portal.repositories.SrnRepository;

import java.util.HashMap;
import java.util.Map;

public class ResponseFactory {
    SrnRepository srnRepository;
    FormRequest formRequest;

    public HashMap<String, Integer> generateBaseReport() {
        HashMap<String, Integer> report = new HashMap<>();

//        report.put("allSpbLo", srnRepository.countAll());
        report.put("allSpb", srnRepository.countAllByDepartment_RegCode(1140));
        report.put("allSpbNotFixed", srnRepository.countAllByDepartment_RegCodeAndResult_Fixed(1140, false));
        report.put("allLo", srnRepository.countAllByDepartment_RegCode(1141));
        report.put("allLoNotFixed", srnRepository.countAllByDepartment_RegCodeAndResult_Fixed(1141, false));

        return report;
    }

    public String generateReport(FormRequest formRequest) {
        HashMap<String, Integer> report = new HashMap<>();
//        report.put("Всего по Санкт-Петербургу и Ленинградской области", srnRepository.countAll());
        return toHTML(report);
    }

    public String toHTML(HashMap<String, Integer> report) {
        StringBuilder sb = new StringBuilder();

        sb.append("<table>").append("<tr>");
        for (Map.Entry<String, Integer> pair : report.entrySet()) {
            sb.append("<td>").append(pair.getKey()).append("</td>").append("<td>").append(pair.getValue()).append("</td");
        }
        sb.append("</tr>").append("</table>");
        return sb.toString();
    }
}
