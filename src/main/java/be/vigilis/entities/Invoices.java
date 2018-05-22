package be.vigilis.entities;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Invoices")
public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String received;
    private LocalDate confirmationDate;
    private String state;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<File> files;

    public Invoices() {
    }

    public Invoices(String received, LocalDate confirmationDate, String state, List<File> files) {
        this.received = received;
        this.confirmationDate = confirmationDate;
        this.state = state;
        this.files = files;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public LocalDate getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(LocalDate confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<File> getFile() {
        return files;
    }

    public void setFile(List<File> files) {
        this.files = files;
    }
}
