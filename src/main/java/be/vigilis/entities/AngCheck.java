package be.vigilis.entities;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;


@Entity
public class AngCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private boolean positive;
    private LocalDate dateAppendix3;
    private File appendix3;
    private LocalDate requestedCheck;
    private LocalDate receivedCheck;
    private LocalDate advice;


    public AngCheck() {
    }

    public AngCheck(boolean positive, LocalDate dateAppendix3, File appendix3, LocalDate requested, LocalDate received) {
        this.positive = positive;
        this.dateAppendix3 = dateAppendix3;
        this.appendix3 = appendix3;
        this.requestedCheck = requested;
        this.receivedCheck = received;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPositive() {
        return positive;
    }

    public void setPositive(boolean positive) {
        this.positive = positive;
    }

    public LocalDate getDateAppendix3() {
        return dateAppendix3;
    }

    public void setDateAppendix3(LocalDate dateAppendix3) {
        this.dateAppendix3 = dateAppendix3;
    }

    public File getAppendix3(){
        return appendix3;
    }

    public void setAppendix3(File appendix3) {
        this.appendix3 = appendix3;
    }

    public LocalDate getRequestedCheck() {
        return requestedCheck;
    }

    public void setRequestedCheck(LocalDate requested) {
        this.requestedCheck = requested;
    }

    public LocalDate getReceivedCheck() {
        return receivedCheck;
    }

    public void setReceivedCheck(LocalDate received) {
        this.receivedCheck = received;
    }

    public LocalDate getAdvice() {
        return advice;
    }

    public void setAdvice(LocalDate advice) {
        this.advice = advice;
    }
}
