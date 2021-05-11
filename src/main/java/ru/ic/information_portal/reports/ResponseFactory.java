package ru.ic.information_portal.reports;

import ru.ic.information_portal.reports.FormRequest;
import ru.ic.information_portal.repositories.SrnRepository;

public class ResponseFactory {
    SrnRepository srnRepository;
    FormRequest formRequest;

    public ResponseFactory(FormRequest formRequest) {
        this.formRequest = formRequest;
    }

    public int generate(FormRequest formRequest) {
        int report = 0;

        report = srnRepository.countAllByDepartment_CodeAndResult_IdIsLike(formRequest.getDepartment().getCode(), formRequest.getResult().getId());
        return report;
    }
}
