package dev.luizribx.fightsystem.domain;

import dev.luizribx.fightsystem.enums.StatusInvoicesEnum;
import dev.luizribx.fightsystem.enums.StatusRegistrationsEnum;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class DomainLifecycleTest {

    @Test
    void studentsPrePersistAndPreUpdateFillTimestamps() {
        StudentsDomain student = new StudentsDomain();

        student.prePersist();
        student.preUpdate();

        assertThat(student.getCreatedAt()).isNotNull();
        assertThat(student.getUpdatedAt()).isNotNull();
    }

    @Test
    void attendancePrePersistFillsEntryDateOnlyWhenMissing() {
        AttendanceDomain attendance = new AttendanceDomain();

        attendance.prePersist();

        assertThat(attendance.getEntryDate()).isNotNull();
    }

    @Test
    void attendancePrePersistKeepsExistingEntryDate() {
        AttendanceDomain attendance = new AttendanceDomain();
        LocalDate originalDate = LocalDate.of(2026, 6, 1);
        attendance.setEntryDate(originalDate.atTime(8, 0));

        attendance.prePersist();

        assertThat(attendance.getEntryDate()).isEqualTo(originalDate.atTime(8, 0));
    }

    @Test
    void registrationsPrePersistFillsSubscriptionDateOnlyWhenMissing() {
        RegistrationsDomain registration = new RegistrationsDomain();

        registration.prePersist();

        assertThat(registration.getDateSubscription()).isNotNull();
    }

    @Test
    void registrationsPrePersistKeepsExistingSubscriptionDate() {
        RegistrationsDomain registration = new RegistrationsDomain();
        LocalDate originalDate = LocalDate.of(2026, 5, 10);
        registration.setDateSubscription(originalDate);

        registration.prePersist();

        assertThat(registration.getDateSubscription()).isEqualTo(originalDate);
    }

    @Test
    void registrationsModalitiesPrePersistFillsStartDateOnlyWhenMissing() {
        RegistrationsModalitiesDomain registrationModality = new RegistrationsModalitiesDomain();

        registrationModality.prePersist();

        assertThat(registrationModality.getStartDate()).isNotNull();
    }

    @Test
    void registrationsModalitiesPrePersistKeepsExistingStartDate() {
        RegistrationsModalitiesDomain registrationModality = new RegistrationsModalitiesDomain();
        LocalDate originalDate = LocalDate.of(2026, 5, 10);
        registrationModality.setStartDate(originalDate);

        registrationModality.prePersist();

        assertThat(registrationModality.getStartDate()).isEqualTo(originalDate);
    }

    @Test
    void entitiesStartWithExpectedDefaultValues() {
        assertThat(new InvoicesRegistrationsDomain().getStatus()).isEqualTo(StatusInvoicesEnum.ABERTA);
        assertThat(new RegistrationsDomain().getStatus()).isEqualTo(StatusRegistrationsEnum.ATIVA);
        assertThat(new ModalitiesDomain().getActive()).isTrue();
        assertThat(new SubscriptionDomain().getActive()).isTrue();
    }
}
