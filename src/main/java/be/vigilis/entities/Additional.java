package be.vigilis.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Additional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private LocalDate requested;
    private LocalDate expiration;
    private LocalDate receiveDate;
    private LocalDate continuity;
    private LocalDate notification;
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<File> additionalDocuments;
    private LocalDate charge;

    public Additional() {
    }

    public Additional(LocalDate requested, LocalDate expiration, LocalDate receiveDate, LocalDate continuity,
                      LocalDate notification, List<File> additionalDocuments, LocalDate charge) {
        this.requested = requested;
        this.expiration = expiration;
        this.receiveDate = receiveDate;
        this.continuity = continuity;
        this.notification = notification;
        this.additionalDocuments = additionalDocuments;
        this.charge = charge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRequested() {
        return requested;
    }

    public void setRequested(LocalDate requested) {
        this.requested = requested;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public LocalDate getContinuity() {
        return continuity;
    }

    public void setContinuity(LocalDate continuity) {
        this.continuity = continuity;
    }

    public LocalDate getNotification() {
        return notification;
    }

    public void setNotification(LocalDate notification) {
        this.notification = notification;
    }

    public List<File> getAdditionalDocuments() {
        return additionalDocuments;
    }

    public void setAdditionalDocuments(List<File> additionalDocuments) {
        this.additionalDocuments = additionalDocuments;
    }

    public LocalDate getCharge() {
        return charge;
    }

    public void setCharge(LocalDate charge) {
        this.charge = charge;
    }
}
