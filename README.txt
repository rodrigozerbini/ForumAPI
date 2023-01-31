Methods to try the application on Postman

- Get all users (GET)
http://localhost:8080/forum/users

-----------------------------------------------------------------

- Get all threads (GET)
http://localhost:8080/forum/threads

-----------------------------------------------------------------

- Get all posts (GET)
http://localhost:8080/forum/posts

-----------------------------------------------------------------

- Get user by ID (GET)
http://localhost:8080/forum/users/{userId}

Example for userId = 1:
http://localhost:8080/forum/users/1

-----------------------------------------------------------------

- Log in (POST)
http://localhost:8080/forum/users/login

Body example:
{
    "email": "alex@example.com",
    "password": "postman"
}

-----------------------------------------------------------------

Save user (POST)
http://localhost:8080/forum/users/add

Body example:
{
    "email": "miguel@example.com",
    "username": "miguel",
    "password": "hi"
}

-----------------------------------------------------------------

Save thread (POST)
http://localhost:8080/forum/threads/users/{userId}

Example to create a thread logged in with userId = 1:
http://localhost:8080/forum/threads/users/1

Body example:

{
    "title": "Alex's thread 2",
    "creationDate": "07/10/2022"
}

-----------------------------------------------------------------

Save post (POST)
http://localhost:8080/forum/threads/{threadId}

Example to create a post in thread with id 5:
http://localhost:8080/forum/threads/5

Body example:


{
    "title": "Banned test",
    "body": "This should not work because has a banned word: d***",
    "image": "My image"
}
-----------------------------------------------------------------

Delete post (DELETE)
http://localhost:8080/forum/users/{userId}

Example to delete user with id = 5:

http://localhost:8080/forum/users/5

-----------------------------------------------------------------

Delete thread (DELETE)
http://localhost:8080/forum/threads/{threadId}

Example to delete thread with id = 2:
http://localhost:8080/forum/threads/2

-----------------------------------------------------------------

Delete Post (DELETE)
http://localhost:8080/forum/posts/{postId}

Example to delete post with id = 3:
http://localhost:8080/forum/posts/3

-----------------------------------------------------------------
