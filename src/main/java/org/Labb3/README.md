# Laboration 3 - Warehouse


1. class Warehouse
    1. All functionality has to have a test
    2. Add new product, require name when created
    3. Modify existing product, (change name, category...)
    4. get all products
    5. find a product with unique id
    6. get all products sorted after name (a-z)
    7. Get all products created after a certain date
    8. Get all products modified after being created( dateCreated != dateLastModified)
2. class Product
   1. Fields
      4. ~~id(unique)~~
         Use UUID.randomUUID()
      5. ~~name~~
      6. ~~category (enum of categories)~~
         Arrays.toString()
      7. rating(1-10)
         ~~Use enum here too?~~ 
      8. ~~date created~~
         new Date(); .after / before 
      9. ~~date last modified~~
         
   2. Has to be immutable. 
      1. https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html


### Extra

Extend functionality in warehouse for:

* get all products that have 1 category linked
* get all products in a category
* get a Map that has initial letters as key and amount of products as value
* get all products with max rating, created this month, and sorted by date descending.