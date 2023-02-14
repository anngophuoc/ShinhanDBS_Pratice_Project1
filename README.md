# PRACTICE PROJECT 1
# REST CRUD APIs WITH H2 DATABASE AND JPA
***

## POST /api/tutorials
Test API Create new tutorials
![Create new tutorials](testapi_image/create-new-tutorials.png "Create new tutorials" )
## GET /api
### GET /api/tutorials
Test API Get all tutorials
![Get_all_tutorials](testapi_image/get-all-tutorials.png "Get_all_tutorials")
### GET /api/tutorials/{id}
Test API Get a specific tutorial by id (here id = 2)
![Get_tutorial_by_id2](testapi_image/get-tutorial-by-id2-before-update.png "Get_tutorial_by_id2")

## PUT /api/tutorials/{id}
Test API update a specific tutorial by id.
* This is "tutorial2" before updating
![Tutorial2-before_update](testapi_image/get-tutorial-by-id2-before-update.png)
* After updating
![Tutorial2-after-update](testapi_image/after-update-tutorial2.png "Tutorial2-after-update")

## GET /api/tutorials/published
Get only published tutorials
![Get-all-published-tutorials](testapi_image/get-all-tutorials-published.png "Get-all-published-tutorials")
## GET /api/tutorials?title=[keywork]
Test API get tutorials that have keyword in their title
![Get-tutorial-with-keyword](testapi_image/get-tutorial-with-keyword.png "Get-tutorial-with-keyword")

## DELETE /api
### DELETE /api/tutorials/{id}
Delete tutorial by given id
* Before deleting
![Before-deleting-a-tutorial](testapi_image/before-delete-one-tutorial.png)
* After deleting tutorial id = 1
![After-deleting-tutorial-1](testapi_image/after-delete-tutorial1.png "After-deleting-tutorial-1")
### DELETE /api/tutorials
Delete all tutorials in database
![Delete-all-tutorials](testapi_image/delete-all-tutorial.png "Delete-all-tutorials")



