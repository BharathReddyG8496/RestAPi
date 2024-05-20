package com.bharath.rest.webservices.restfulwebservices.Filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;


//@JsonIgnoreProperties("crimeDate") using this you can list the fields that you dont want to be sent to the client
@JsonFilter("PoliceCaseFilter")
public class PoliceCase {

   // @JsonIgnore implements static filtering, it makes the field not to appear in the result
    private String caseId;
    private String Description;

    private String placeOfCrime;

    private LocalDate crimeDate;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPlaceOfCrime() {
        return placeOfCrime;
    }

    public void setPlaceOfCrime(String placeOfCrime) {
        this.placeOfCrime = placeOfCrime;
    }

    public LocalDate getCrimeDate() {
        return crimeDate;
    }

    public void setCrimeDate(LocalDate crimeDate) {
        this.crimeDate = crimeDate;
    }

    public PoliceCase(String caseId, String description, String placeOfCrime, LocalDate crimeDate) {
        this.caseId = caseId;
        Description = description;
        this.placeOfCrime = placeOfCrime;
        this.crimeDate = crimeDate;
    }

    @Override
    public String toString() {
        return "PoliceCase{" +
                "caseId='" + caseId + '\'' +
                ", Description='" + Description + '\'' +
                ", placeOfCrime='" + placeOfCrime + '\'' +
                ", crimeDate=" + crimeDate +
                '}';
    }
}
