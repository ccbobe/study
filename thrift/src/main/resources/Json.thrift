namespace java com.ccbobe.thrift
struct Request{
    1:optional string msgId
    2:optional string code;
    3:optional string data;
}
struct Response{
    1:optional string msgId
    2:optional string code;
    3:optional string data;
}

service DateService{
    Response getDate();
}