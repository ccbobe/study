namespace java com.ccbobe.thrift
struct User {
    1: i64 userId
    2: string userName
    3: string addr
    4: i32 age
    5: string url
}
service UserService{

    User addUser(1: User user);

    User getUser(2:i64 userId);

    list<User> queryUsers();

    User updateUser( 1: User user);
}