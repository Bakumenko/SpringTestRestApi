# StartupRestSpringTestApp
## This is my test rest api application for learning spring boot java.
This app is for users with role ROLE_USER who can create projects. For administration app have users with role ROLE_ADMIN. They can create do all (alos create new roles). 

Users with ROLE_CLIENT can get all projects, get all users with ROLE_USER (without admins) and create projects. If admin will delete user with project, then project will deleted too.

This app is used bearer jwt token for authorization. Without authorization you can do only regitration (/register) and authorization (/auth).

### Current tables
1) Users 

    id          Long
       
    username    string,
    
    password    string,
    
    first_name  string,
    
    last_name   string,
    
    username    string,
    
    role_id     int

2) Roles

    id   Long

    name string

3) Projects

    id Long
    
    title       string
        
    description string
        
    income      int
        
    user_id     int
   
Api routes:
https://www.getpostman.com/collections/1334bf271c8792f34e9f
