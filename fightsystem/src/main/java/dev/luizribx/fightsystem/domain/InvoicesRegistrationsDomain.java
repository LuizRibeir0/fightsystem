package dev.luizribx.fightsystem.domain;

import dev.luizribx.fightsystem.enums.StatusInvoicesEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices_registrations")
public class InvoicesRegistrationsDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "due_date")
    private LocalDate dueDate;

    private BigDecimal price;

    @Column(name = "pay_day")
    private LocalDateTime payDay;

    @Column(name = "cancellation_date")
    private LocalDate cancellationDate;

    @Enumerated(EnumType.STRING)
    private StatusInvoicesEnum status = StatusInvoicesEnum.ABERTA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_id")
    private RegistrationsDomain registrations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getPayDay() {
        return payDay;
    }

    public void setPayDay(LocalDateTime payDay) {
        this.payDay = payDay;
    }

    public LocalDate getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(LocalDate cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public StatusInvoicesEnum getStatus() {
        return status;
    }

    public void setStatus(StatusInvoicesEnum status) {
        this.status = status;
    }

    public RegistrationsDomain getRegistrations() {
        return registrations;
    }

    public void setRegistrations(RegistrationsDomain registrations) {
        this.registrations = registrations;
    }
}
