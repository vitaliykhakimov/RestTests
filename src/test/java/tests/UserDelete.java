package tests;

import org.junit.Before;
import org.junit.Test;
import users.User;
import utils.Basic;
import utils.UserCreate;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDelete extends Basic{
    private User postUser;
    private String userLocation;

    @Before
    public void setUp() {
        postUser = UserCreate.createDummyUser();
    }

    @Test
    public void shouldDeleteUser() {
        userLocation = createResource("user/", postUser);
        assertThat(deleteByIdResource(userLocation)).isTrue();
    }

    @Test
    public void deleteNotPresentUser() {
        assertThat(deleteByIdResource("user/100")).isFalse();
    }

    @Test
    public void deleteAllUsers() {
        assertThat(deleteAllResources("user/")).isTrue();
    }
}
