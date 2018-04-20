package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTestShould {

    @Mock
    private TripDAO tripDAO;

    @InjectMocks
    @Spy
    TripService tripService = new TripService();

    @Test(expected = UserNotLoggedInException.class)
    public void
    throws_exception_if_user_not_logged_in() {

        User user = new User();
        tripService.getFriendsTrips(user, null);
    }

    @Test
    public void
    find_trips_when_user_is_friend_with_logged_in_user() {
        User user = new User();
        User loggedInUser = new User();
        user.addFriend(loggedInUser);
        List<Trip> expectedListOfTrips = new ArrayList<>();
        expectedListOfTrips.add(new Trip());
        given(tripDAO.tripsBy(user)).willReturn(expectedListOfTrips);

        List<Trip> result = tripService.getFriendsTrips(user, loggedInUser);

        assertThat(result, is(expectedListOfTrips));
    }

    @Test
    public void
    not_find_trips_when_user_is_not_friend_with_logged_in_user() {
        User user = new User();
        User loggedInUser = new User();
        List<Trip> expectedListOfTrips = new ArrayList<>();
        expectedListOfTrips.add(new Trip());
        given(tripDAO.tripsBy(user)).willReturn(expectedListOfTrips);

        List<Trip> result = tripService.getFriendsTrips(user, loggedInUser);

        assertThat(result.size(), is(0));
        assertNotSame(result, expectedListOfTrips);
    }
}
