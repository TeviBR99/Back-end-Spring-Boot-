package com.capgemini.ccsw.tutorial.prestamos.model;

import org.springframework.data.domain.Pageable;

public class PrestamosSearchDto {
    private Pageable pageable;

    public Pageable getPageable() {
        return this.pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

}
