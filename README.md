# Reddit_Android_App

# FETCH

## Table of Contents
1. [Overview](#Overview)
2. [User Stories](#User-Stories)
3. [Video Walkthrough](#Video-Walkthrough)
4. [Wireframes](#Wireframes)
5. [Notes](#Notes)
6. [Open-source libraries used](#Open-source-libraries-used)
7. [Schema](#Schema)

## Overview
### Description
   - Application retrives posts from Reddit Api -Listing endpoint (https://www.reddit.com/dev/api) to display title and thumbnails.
   - The comments for each post will be retrieved and displayed on clicking a post

## User Stories
Required features:
- [x] Display a list of reddit listings with the thumbnail and title
- [x] This list should be able to be paginated
- [x] Clicking on one of the rows should take the user to a view with the comments for that listing.

Additional features:
- [x] Added Author for each reddit listing and for each comment
- [x] Replaced with reddit default images in listings without thumbnail details

<img src='https://github.com/Athma-Vaishali/Reddit_Android_App/blob/master/handling_thumbail_path.JPG' />

## Video Walkthrough
<img src='https://github.com/Athma-Vaishali/Reddit_Android_App/blob/master/walkthrough.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

## Wireframes

### Digital Wireframes & Mockups

## Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing

## Schema 
### Models
#### Post
Retrieved from listing endpoint (https://www.reddit.com/r/all/top.json)

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | after      | String   | Parameter to retrieve next page of listings |
   | id      | String   | Unique id for each post to retrieve comments |
   | author        | String | Author of the post |
   | title         | String     | Title of the post |
   | thumbnail_path         | String     | Link for the thumbnail image |
   
#### Comment
Retrieved from comments endpoint (ex: https://www.reddit.com/r/all/comments/nzp2n0.json)

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | author      | String   | Author of the comment |
   | body        | String | Comment string |
      
### Networking
#### List of network requests by screen
   - Main Screen
        - (Read/GET) Retrieve posts from listing endpoint    
        (https://www.reddit.com/r/all/top.json)
        - (Read/GET) Retrieve posts from listing endpoint with after parameter to get next page    
        (ex: https://www.reddit.com/r/all/top.json?after=t3_p83dpa)
        
   - Comment Screen
        - (Read/GET) Retrieve comments from comments endpoint 
        (ex: https://www.reddit.com/r/all/comments/nzp2n0.json)
        
   ```swift  
   AsyncHttpClient client = new AsyncHttpClient();
   client.get(URL, new JsonHttpResponseHandler() {
   @Override
   public void onSuccess(int i, Headers headers, JSON json) {
          Log.d(TAG,"onSuccess");
          JSONArray jsonArray=json.jsonArray;
          items.addAll(...));
   }      
   @Override
   public void onFailure(int i, Headers headers, String s, Throwable throwable) {
          Log.d(TAG,"onFailure",throwable);
   }
  });
