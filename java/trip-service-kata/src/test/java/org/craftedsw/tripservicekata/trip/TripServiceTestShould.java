package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TripServiceTestShould extends TripService{

    UserSession userSession = mock(UserSession.class);
    private List<Trip> stubListOfTrips;

    @Test(expected = UserNotLoggedInException.class) public void
    throws_exception_if_user_not_logged_in(){
        given(userSession.getLoggedUser()).willReturn(null);
        User user = new User();
        getTripsByUser(user);
    }

    @Test public void
    find_trips_when_user_is_friend_with_logged_in_user(){
        User user = new User();
        User loggedInUser = new User();

        given(userSession.getLoggedUser()).willReturn(loggedInUser);
        user.addFriend(loggedInUser);

        List<Trip> expectedListOfTrips = new ArrayList<>();
        expectedListOfTrips.add(new Trip());
        makeDaoReturn(expectedListOfTrips);
        List<Trip> result= getTripsByUser(user);

        assertThat(result, is(expectedListOfTrips));

    }

    @Test public void
    not_find_trips_when_user_is_not_friend_with_logged_in_user(){
        User user = new User();
        User loggedInUser = new User();

        given(userSession.getLoggedUser()).willReturn(loggedInUser);

        List<Trip> expectedListOfTrips = new ArrayList<>();
        expectedListOfTrips.add(new Trip());
        makeDaoReturn(expectedListOfTrips);
        List<Trip> result= getTripsByUser(user);

        assertThat(result.size(), is(0));
        assertNotSame(result, expectedListOfTrips);

    }

    private void makeDaoReturn(List<Trip> expectedListOfTrips) {
        stubListOfTrips = expectedListOfTrips;
    }


    @Override
    protected UserSession getUserSession() {
        return userSession;
    }

    @Override
    protected List<Trip> findTripsByUser(User user) {
        return stubListOfTrips;
    }
}
