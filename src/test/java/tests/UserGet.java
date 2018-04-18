package tests;

import org.junit.Before;
import org.junit.Test;
import users.User;
import utils.Basic;
import utils.UserCreate;

import static org.assertj.core.api.Assertions.assertThat;

public class UserGet extends Basic {
    private User getUser;
    private User presentUser;

    @Before
    public void setUp() {
        presentUser = UserCreate.creatingUserWithPresentName();
    }

    @Test
    public void shouldGetUser() {
        getUser = getResource("user/4", User.class);
        assertThat(getUser).isEqualToComparingFieldByField(presentUser);
    }

    @Test(expected = AssertionError.class)
    public void getAbsentUser() {
        getUser = getResource("user/66", User.class);
    }
}
