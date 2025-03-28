package com.example.ums.data.cust;

import com.example.ums.code.EnableType;
import com.example.ums.data.Contact;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired private CustRepository custRepository;
    @Autowired private UserRepository userRepository;

    @BeforeEach
    void saveCust() {
        Cust departmentA = new Cust("kbbank-A", "영업A팀", "");
        Cust departmentB = new Cust("kbbank-B", "영업B팀", "");
        Cust cust = new Cust("kbbank","국민은행","",departmentA);
        custRepository.save(cust);
    }


    @Test
    @DisplayName("사용자 등록")
    void addUser() {
        // given
        Cust cust = custRepository.findById("kbbank-A").get();
        User user = new User(cust, new Contact("01012345667", "jang314@naver.com"), "jang314", "12345", "장혜진");

        // when
        User saveUser = userRepository.save(user);

        // then
        assertThat(saveUser.getUserId()).isEqualTo(user.getUserId());
        assertThat(saveUser.getName()).isEqualTo(user.getName());
        assertThat(saveUser.getContact().getEmail()).isEqualTo(user.getContact().getEmail());
        assertThat(saveUser.getContact().getPhone()).isEqualTo(user.getContact().getPhone());
        assertThat(saveUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(saveUser.getCust().getId()).isEqualTo("kbbank-A");
    }

    @Test
    @DisplayName("사용자 조회")
    void findUser() {
        // given
        Optional<Cust> cust = custRepository.findById("kbbank-A");
        User user = new User(cust.get(), new Contact("01012345667", "jang314@naver.com"), "jang314", "12345", "장혜진");
        userRepository.save(user);

        // when
        User findUser = userRepository.findById(user.getUserId()).get();

        // then
        assertThat(findUser.getUserId()).isEqualTo(user.getUserId());
        assertThat(findUser.getName()).isEqualTo(user.getName());
        assertThat(findUser.getContact().getEmail()).isEqualTo(user.getContact().getEmail());
        assertThat(findUser.getContact().getPhone()).isEqualTo(user.getContact().getPhone());
        assertThat(findUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(findUser.getCust().getId()).isEqualTo("kbbank-A");

    }

    @Test
    @DisplayName("사용자 수정")
    void updateUser() {
        // given
        Optional<Cust> cust = custRepository.findById("kbbank-A");
        User saveUser = userRepository.save(new User(cust.get(), new Contact("01012345667", "jang314@naver.com"), "jang314", "12345", "장혜진"));

        // when
        Optional<Cust> findCust = custRepository.findById("kbbank-B");
        User updateUser = userRepository.save(new User(findCust.get(),
                new Contact("01012345678", "jang314@kakao.com"),
                saveUser.getUserId(), "1234", "장혜진11"));
        User result = userRepository.findById(updateUser.getUserId()).get();

        // then
        assertThat(result.getUserId()).isEqualTo(updateUser.getUserId());
        assertThat(result.getName()).isEqualTo(updateUser.getName());
        assertThat(result.getContact().getEmail()).isEqualTo(updateUser.getContact().getEmail());
        assertThat(result.getContact().getPhone()).isEqualTo(updateUser.getContact().getPhone());
        assertThat(result.getPassword()).isEqualTo(updateUser.getPassword());
        assertThat(result.getCust().getId()).isEqualTo("kbbank");

        Cust updateCust = custRepository.save(new Cust("wooribank", "우리은행", ""));
        User updateUserWithCust = new User(updateCust, result.getContact(), result.getUserId(), result.getPassword(), result.getName());

        User updateCustResult = userRepository.save(updateUserWithCust);
        User custResult = userRepository.findById(updateCustResult.getUserId()).get();
        assertThat(custResult.getCust().getId()).isEqualTo(updateCust.getId());
    }

    @Test
    @DisplayName("사용자 삭제")
    void deleteUser() {
        // given
        Optional<Cust> cust = custRepository.findById("kbbank");
        User user = new User(cust.get(), new Contact("01012345667", "jang314@naver.com"), "jang314", "12345", "장혜진");
        User saveUser = userRepository.save(user);

        User deleteUser = userRepository.save(User.disableUser(saveUser));
        Optional<User> findUser = userRepository.findById(deleteUser.getUserId());

        assertThat(findUser.get().getEnable()).isEqualTo(EnableType.N);
    }

}