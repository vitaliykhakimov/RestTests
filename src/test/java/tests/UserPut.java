package tests;

import org.junit.Before;
import org.junit.Test;
import users.User;
import utils.Basic;
import utils.UserCreate;

import static org.assertj.core.api.Assertions.assertThat;

public class UserPut extends Basic {
    private User postUser;
    private String userLocation = "user/4";
    private User getUser;

    @Before
    public void setUp() {
        postUser = UserCreate.createDummyUser();
    }

    @Test
    public void shouldPutUser() {
        putResource(userLocation, postUser);
        getUser = getResource(userLocation, User.class);
        assertThat(getUser).isEqualToIgnoringGivenFields(postUser, "id");
    }

    @Test(expected = AssertionError.class)
    public void putNotExistingUser() {
        userLocation = "/user/20";
        putResource(userLocation, postUser);
    }
}
