package be.vigilis.entities;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "General")
public class General {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "Language")
    private String language;
    @Column(name = "ApplicationDate")
    private LocalDate application;
    @Column(name = "KboName", unique = true)
    private String nameKbo;
    @Column(name = "KboNumber")
    private long kboNumber;
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;
    @Column
    private String applicationType;
    @Column
    private String notes;
    @JoinColumn(name = "id_invoices", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Invoices invoice;
    @JoinColumn(name = "id_additional", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Additional additional;
    @ElementCollection(fetch = FetchType.LAZY)
    private List <Employee> employees = new ArrayList();
    private File appendix1;

    public General() {
    }

    public General(String language, LocalDate application, String nameKbo, long kboNumber, Address address,
                   String applicationType, String notes, Invoices invoice, Additional additional, List<Employee> employees,
                   File apendix1) {
        this.language = language;
        this.application = application;
        this.nameKbo = nameKbo;
        this.kboNumber = kboNumber;
        this.address = address;
        this.applicationType = applicationType;
        this.notes = notes;
        this.invoice = invoice;
        this.additional = additional;
        this.employees = employees;
        this.appendix1 = apendix1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDate getApplication() {
        return application;
    }

    public void setApplication(LocalDate application) {
        this.application = application;
    }

    public String getNameKbo() {
        return nameKbo;
    }

    public void setNameKbo(String nameKbo) {
        this.nameKbo = nameKbo;
    }

    public long getKboNumber() {
        return kboNumber;
    }

    public void setKboNumber(long kboNumber) {
        this.kboNumber = kboNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Invoices getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoices invoice) {
        this.invoice = invoice;
    }

    public Additional getAdditional() {
        return additional;
    }

    public void setAdditional(Additional additional) {
        this.additional = additional;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public File getAppendix1() {
        return appendix1;
    }

    public void setAppendix1(File appendix1) {
        this.appendix1 = appendix1;
    }
}
