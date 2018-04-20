package org.craftedsw.tripservicekata.user;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserShould {

    private User user;
    private User aUser;

    @Before
    public void setUp() {
        this.user = new User();
        this.aUser = new User();
    }

    @Test
    public void
    inform_when_users_are_not_friends() {
        assertThat(user.isFriendsWith(aUser), is(false));
    }

    @Test
    public void
    inform_when_users_are_friends() {
        user.addFriend(aUser);

        assertThat(user.isFriendsWith(aUser), is(true));
    }

}
