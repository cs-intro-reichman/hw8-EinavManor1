

/** Represents a social network. The network has users, who follow other uesrs.
 *  Each user is an instance of the User class. */
public class Network {

    // Fields
    private User[] users;  // the users in this network (an array of User objects)
    private int userCount; // actual number of users in this network

    /** Creates a network with a given maximum number of users. */
    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    /** Creates a network  with some users. The only purpose of this constructor is 
     *  to allow testing the toString and getUser methods, before implementing other methods. */
    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    public int getUserCount() {
        return this.userCount;
    }
    /** Finds in this network, and returns, the user that has the given name.
     *  If there is no such user, returns null.
     *  Notice that the method receives a String, and returns a User object. */
    public User getUser(String name) {
        for (int i = 0; i < userCount; i++) {
            if (name.equals(users[i].getName())) {
                return users[i];
            }
        }
        return null;
    }

    /** Adds a new user with the given name to this network.
    *  If ths network is full, does nothing and returns false;
    *  If the given name is already a user in this network, does nothing and returns false;
    *  Otherwise, creates a new user with the given name, adds the user to this network, and returns true. */
    public boolean addUser(String name) {
        if (userCount >= users.length || getUser(name) != null) {
            return false;
        }
        users[userCount] = new User(name);
        userCount++;
        return true;
    }

    /** Makes the user with name1 follow the user with name2. If successful, returns true.
     *  If any of the two names is not a user in this network,
     *  or if the "follows" addition failed for some reason, returns false. */
    public boolean addFollowee(String name1, String name2) {
        if (name1 == null || name2 == null) {
            return false;
        }
        User user1 = getUser(name1);
        User user2 = getUser(name2);
    
        if (user1 == null || user2 == null) {
            return false;
        }
        if (name1.equals(name2)) {
            return false;
        }
        return user1.addFollowee(user2.getName());
    }
    
    /** For the user with the given name, recommends another user to follow. The recommended user is
     *  the user that has the maximal mutual number of followees as the user with the given name. */
    public String recommendWhoToFollow(String name) {
        User recommendFollow = null; 
        int maxMutual = 0;
        User user = getUser(name);
        for(int i = 0; i < getUserCount() ; i++){
            User other = users[i];
            if (user == other || user.follows(other.getName())) {
                continue;
            }
            int mutualCount = user.countMutual(other);
            if (mutualCount > maxMutual) {
                maxMutual = mutualCount;
                recommendFollow = other;
            }
        }
        return recommendFollow.getName();
    }
    

    /** Computes and returns the name of the most popular user in this network: 
     *  The user who appears the most in the follow lists of all the users. */
    public String mostPopularUser() {
        if(getUserCount() == 0){
            return null;
        }
        User mostPopular = null; 
        int maxFollowers = 0;
        for(int i = 0; i < getUserCount(); i++){
            User user = users[i];
            int count = 0;
            for(int j = 0; j < getUserCount(); j++){
                User userCheck = users[j];
                if(userCheck.follows(user.getName())){
                    count++;
                }
            }
        if(count > maxFollowers){
            maxFollowers = count;
            mostPopular = user;
        }
    }
        return mostPopular.getName();
    }

    /** Returns the number of times that the given name appears in the follows lists of all
     *  the users in this network. Note: A name can appear 0 or 1 times in each list. */
    private int followeeCount(String name) {
        if(getUserCount() == 0){
            return 0;
        }
        User user = getUser(name);
        int count = 0;
        for(int j = 0; j < getUserCount(); j++){
            User userCheck = users[j];
            if(userCheck.follows(user.getName())){
                count++;
            }
        }
        return count;
    }

    public String toString(){ 
        if (userCount == 0) {
            return "Network:";
        }
    
        String result = "Network:\n";
    
        for (int i = 0; i < userCount; i++) {
            User user = users[i];
            result += user.getName() + " ->";
    
            String[] followees = user.getfFollows();
            for (int j = 0; j < user.getfCount(); j++) {
                result += " " + followees[j];
            }
    
            if (i < userCount - 1) {
                result += " \n";
            } else {
                result += " ";
            }
        }
    
        return result;
    }
}