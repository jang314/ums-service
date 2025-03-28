package com.example.ums.data.cust;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustRepositoryTest {
    @Autowired
    private CustRepository custRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("고객사 추가")
    void addCust() {
        // given
        final Cust saveCust = new Cust("KB", "KB국민은행", "");

        // when
        final Cust resultCust = custRepository.save(saveCust);

        // then
        assertThat(resultCust.getId()).isEqualTo(saveCust.getId());
        assertThat(resultCust.getName()).isEqualTo(saveCust.getName());
        assertThat(resultCust.getDescription()).isEqualTo(saveCust.getDescription());

    }

    @Test
    @DisplayName("고객사/부서 조회")
    void findCustTest() {
        // given
        final Cust saveCust = givenCust();
        custRepository.save(saveCust);

        // when
        final Cust resultCust = custRepository.findById(saveCust.getId()).get();
        final Cust resultDepartment = custRepository.findById("KBBank-A").get();

        // then
        assertThat(saveCust.getId()).isEqualTo(resultCust.getId());
        assertThat(saveCust.getName()).isEqualTo(resultCust.getName());
        assertThat(saveCust.getDescription()).isEqualTo(resultCust.getDescription());
        assertThat(saveCust.getDepartments().size()).isEqualTo(2);
        assertThat(resultDepartment.getPCust().getId()).isEqualTo(resultCust.getId());
        assertThat(resultDepartment.getDepartments().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("부서 추가")
    void addDepartment() {
        // given
        final Cust departmentA = new Cust("KBBank-A", "영업부서A", "", new Cust("KBBank-A1", "영업부서A1팀", ""));
        final Cust departmentB = new Cust("KBBank-B", "영업부서B", "");
        final Cust saveCust = new Cust("KBBank", "KB국민은행", "", departmentA, departmentB);

        // when
        final Cust resultCust = custRepository.save(saveCust);
        final Cust resultDepartmentA = resultCust.getDepartments().stream()
                .filter(dept -> dept.getId().equals(departmentA.getId()))
                .findAny().get();
        final Cust resultDepartmentB = resultCust.getDepartments().stream()
                .filter(dept -> dept.getId().equals(departmentB.getId()))
                .findAny().get();

        // then
        assertThat(resultCust.getDepartments().size()).isEqualTo(2);
        assertThat(resultDepartmentA.getDepartments().size()).isEqualTo(1);
        assertThat(resultDepartmentA.getPCust().getId()).isEqualTo(saveCust.getId());
        assertThat(resultDepartmentA.getDepartments().get(0).getPCust().getId()).isEqualTo(departmentA.getId());

        assertThat(resultDepartmentB.getDepartments().size()).isEqualTo(0);
        assertThat(resultDepartmentB.getPCust().getId()).isEqualTo(saveCust.getId());
    }


    @Test
    @DisplayName("고객사 수정/부서 삭제")
    void findUpdateCustTest() {
        // given
        final Cust saveCust = custRepository.save(givenCust());
        testEntityManager.flush();
        testEntityManager.clear();

        // when
        final List<Cust> departments = saveCust.getDepartments().stream()
                .filter(dept -> !dept.getId().equals("KBBank-B")) // KBBank-B 삭제
                .collect(Collectors.toList());

        final Cust updateCust = new Cust(saveCust.getId(), "우리은행", "", departments.stream().toArray(Cust[]::new));
        custRepository.save(updateCust);
        testEntityManager.flush();
        testEntityManager.clear();
        final Cust resultCust = custRepository.findById(updateCust.getId()).get();
        final Optional<Cust> resultDept = custRepository.findById("KBBank-B");

        // then
        assertThat(updateCust.getId()).isEqualTo(resultCust.getId());
        assertThat(updateCust.getName()).isEqualTo(resultCust.getName());
        assertThat(updateCust.getDescription()).isEqualTo(resultCust.getDescription());
        assertThat(updateCust.getDepartments().size()).isEqualTo(1);
        assertThat(resultDept).isEmpty();
    }


    private static Cust givenCust() {
        final Cust departmentA = new Cust("KBBank-A", "영업부서A", "",
                new Cust("KBBank-A1", "영업부서A1팀", ""));
        final Cust departmentB = new Cust("KBBank-B", "영업부서B", "");

        return new Cust("KBBank", "KB국민은행", "", departmentA, departmentB);
    }
}