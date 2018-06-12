package be.vigilis.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<File> filesNow;

    public Invoices() {
    }

    public Invoices(String received, LocalDate confirmationDate, String state, List<File> files, List<File> filesNow) {
        this.received = received;
        this.confirmationDate = confirmationDate;
        this.state = state;
        this.files = files;
        this.filesNow = filesNow;
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

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<File> getFilesNow() {
        return filesNow;
    }

    public void setFilesNow(List<File> filesNow) {
        this.filesNow = filesNow;
    }
}
