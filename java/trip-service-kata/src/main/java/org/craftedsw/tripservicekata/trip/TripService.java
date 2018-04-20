package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;

public class TripService {

    private static final List<Trip> NO_TRIPS = Collections.emptyList();

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        if (isFriendsWithLoggedInUser(user))
            return findTripsByUser(user);
        else
            return NO_TRIPS;
    }

    private boolean isFriendsWithLoggedInUser(User user) {
        final User loggedUser = loggedUser();
        return user.getFriends().stream()
                .anyMatch(friend -> friend.equals(loggedUser));
    }

    private User loggedUser() {
        return ofNullable(getUserSession().getLoggedUser())
                .orElseThrow(UserNotLoggedInException::new);
    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected UserSession getUserSession() {
        return UserSession.getInstance();
    }

}
