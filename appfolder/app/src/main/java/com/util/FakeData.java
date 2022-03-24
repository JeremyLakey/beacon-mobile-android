package com.util;

import com.models.Friend;
import com.models.AuthToken;
import com.models.Beacon;
import com.models.LocationData;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;


public class FakeData {
    //Marb 40.2468, -111.6490
    private static float LAT_BASE = 40.2468f;
    private static float LONG_BASE = -111.6490f;

    private static Random random = new Random();
    /**
     * Test user profile images.
     */
    private static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
    private static final String FEMALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png";

    /**
     * Generated users.
     */
    private static final Friend user1 = new Friend("Allen Anderson", "@allen", MALE_IMAGE_URL);
    private static final Friend user2 = new Friend("Amy Ames", "@amy", FEMALE_IMAGE_URL);
    private static final Friend user3 = new Friend("Bob Bobson", "@bob", MALE_IMAGE_URL);
    private static final Friend user4 = new Friend("Bonnie Beatty", "@bonnie", FEMALE_IMAGE_URL);
    private static final Friend user5 = new Friend("Chris Colston", "@chris", MALE_IMAGE_URL);
    private static final Friend user6 = new Friend("Cindy Coats", "@cindy", FEMALE_IMAGE_URL);
    private static final Friend user7 = new Friend("Dan Donaldson", "@dan", MALE_IMAGE_URL);
    private static final Friend user8 = new Friend("Dee Dempsey", "@dee", FEMALE_IMAGE_URL);
    private static final Friend user9 = new Friend("Elliott Enderson", "@elliott", MALE_IMAGE_URL);
    private static final Friend user10 = new Friend("Elizabeth Engle", "@elizabeth", FEMALE_IMAGE_URL);
    private static final Friend user11 = new Friend("Frank Frandson", "@frank", MALE_IMAGE_URL);
    private static final Friend user12 = new Friend("Fran Franklin", "@fran", FEMALE_IMAGE_URL);
    private static final Friend user13 = new Friend("Gary Gilbert", "@gary", MALE_IMAGE_URL);
    private static final Friend user14 = new Friend("Giovanna Giles", "@giovanna", FEMALE_IMAGE_URL);
    private static final Friend user15 = new Friend("Henry Henderson", "@henry", MALE_IMAGE_URL);
    private static final Friend user16 = new Friend("Helen Hopwell", "@helen", FEMALE_IMAGE_URL);
    private static final Friend user17 = new Friend("Igor Isaacson", "@igor", MALE_IMAGE_URL);
    private static final Friend user18 = new Friend("Isabel Isaacson", "@isabel", FEMALE_IMAGE_URL);
    private static final Friend user19 = new Friend("Justin Jones", "@justin", MALE_IMAGE_URL);
    private static final Friend user20 = new Friend("Jill Johnson", "@jill", FEMALE_IMAGE_URL);
    private static final Friend user21 = new Friend("John Brown", "@john", MALE_IMAGE_URL);

    private static final String post1 = "I am studying, but rather not.";
    private static final String post2 = "I you're doing nothing, stop by";
    private static final String post3 = "Having a fire with some s'mores";
    private static final String post4 = "Free sour patch kids";
    private static final String post5 = "Playing some Halo";
    private static final String post6 = "Relaxing outside";
    private static final String post7 = "Waiting for a hour";
    private static final String post8 = "Here";
    private static final String post9 = "Watching some Hannah Montana!";
    private static final String post10 = "Chilling by the food court";
    private static final String post11 = "!!!Need 11 people to help move a Farris Wheel!!!";
    private static final String post12 = "Mutual Activity For Rivertown Hills ward";
    private static final String post13 = "I need to talk";
    private static final String post14 = "Hot tub party! Bring your own towel";

    /**
     * Generated auth token.
     */
    private static final AuthToken authToken = new AuthToken();

    /**
     * List of generated users.
     * Used to return lists of followers and followees.
     */
    private static final List<Friend> allFriends = Arrays.asList(
            user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11,
            user12, user13, user14, user15, user16, user17, user18, user19, user20, user21
    );

    /**
     * List of generated Beacon messages
     * Used to return lists of followers and followees.
     */
    private static final List<String> allPosts = Arrays.asList(
            post1, post2, post3, post4, post5, post6, post7, post8, post9, post10,
            post11, post12, post13, post14
    );

    /**
     * List of generated statuses.
     * Used to return lists of story and feed statuses.
     */
    private static final List<Beacon> allBeacons = new ArrayList<Beacon>();

    // Used to force statuses to be re-generated if test cases use
    // different sets of fake users (by mocking the getFakeUsers method).
    private static List<Friend> fakeUsersUsedToGenerateStatuses = null;

    public FakeData() {

        generateFakeBeacons();
        fakeUsersUsedToGenerateStatuses = getFakeFriends();
    }

    private void generateFakeBeacons() {
        allBeacons.clear();

        Calendar calendar = new GregorianCalendar();
        List<Friend> fakeFriends = getFakeFriends();

        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < fakeFriends.size(); ++j) {
                float latitude = LAT_BASE + getRandomLocationValue();
                float longitude = LONG_BASE + getRandomLocationValue();
                Friend sender = fakeFriends.get(j);
                String post = getRandomPost();
                Date countDown = calendar.getTime();
                Beacon beacon = new Beacon((float)latitude,(float)longitude,sender.getUserName(),post,
                        sender,countDown);
                allBeacons.add(beacon);
            }
        }
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public Friend findUserByUniqueId(String alias) {
        List<Friend> fakeUsers = getFakeFriends();
        for (Friend u : fakeUsers) {
            if (u.getUniqueId().equals(alias)) {
                return u;
            }
        }
        return null;
    }

    public LocationData getStartLocationData() {
        return new LocationData(LAT_BASE, LONG_BASE, 0);
    }

    public String getRandomPost() {
        return allPosts.get((int)(random.nextInt(allPosts.size())));
    }

    public int getRandomHour() {
        return random.nextInt(13);
    }

    public float getRandomLocationValue() {
        return random.nextFloat() / 20; // random in about a two mile radius
    }


    // Allows mocking of fake users
    public List<Friend> getFakeFriends() {
        return allFriends;
    }

    // Allows mocking of fake statuses
    public List<Beacon> getFakeBeacons() {
        return allBeacons;
    }

}
