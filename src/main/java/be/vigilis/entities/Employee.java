package be.vigilis.entities;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String rr;
    private String role;
    private File appendix2;
    private File sr;
    private File vo;
    private LocalDate educ;
    private LocalDate fieStart;
    private LocalDate fieEnd;
    private String ifk;
    @JoinColumn(name = "id_angCheck", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private AngCheck angCheck;

    public Employee() {
    }

    public Employee(String name, String rr, String role, File appendix2, File sr, File vo, LocalDate educ,
                    LocalDate fieStart, LocalDate fieEnd, String ifk, AngCheck angCheck) {
        this.name = name;
        this.rr = rr;
        this.role = role;
        this.appendix2 = appendix2;
        this.sr = sr;
        this.vo = vo;
        this.educ = educ;
        this.fieStart = fieStart;
        this.fieEnd = fieEnd;
        this.ifk = ifk;
        this.angCheck = angCheck;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRr() {
        return rr;
    }

    public void setRr(String rr) {
        this.rr = rr;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public File getAppendix2() {
        return appendix2;
    }

    public void setAppendix2(File appendix2) {
        this.appendix2 = appendix2;
    }

    public File getSr() {
        return sr;
    }

    public void setSr(File sr) {
        this.sr = sr;
    }

    public File getVo() {
        return vo;
    }

    public void setVo(File vo) {
        this.vo = vo;
    }

    public LocalDate getEduc() {
        return educ;
    }

    public void setEduc(LocalDate educ) {
        this.educ = educ;
    }

    public LocalDate getFieStart() {
        return fieStart;
    }

    public void setFieStart(LocalDate fieStart) {
        this.fieStart = fieStart;
    }

    public LocalDate getFieEnd() {
        return fieEnd;
    }

    public void setFieEnd(LocalDate fieEnd) {
        this.fieEnd = fieEnd;
    }

    public String getIfk() {
        return ifk;
    }

    public void setIfk(String ifk) {
        this.ifk = ifk;
    }

    public AngCheck isAngCheck() {
        return angCheck;
    }

    public void setAngCheck(AngCheck angCheck) {
        this.angCheck = angCheck;
    }
}
