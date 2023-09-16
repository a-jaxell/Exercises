# Laboration 3 - Warehouse

1. class Warehouse
    1. All functionality has to have a test
        1. TDD - red - green - refactor
    2. Add new product, require name when created
        1. ~~method for adding new product to warehouse~~
        2. ~~Somewhere to put the products (ArrayList, List)~~
    3. ~~Modify existing product, (change name, category...)~~
        1. ~~setters for name and other fields~~
        2. run a private method inside of product
    4. get all products
       Return a copy of the array that stores the objects
    5. ~~find a product with unique id~~
        1. ~~find using the UUID~~
    6. ~~get all products sorted after name (a-z)~~
        1. Create a natural order using CompareTo (Used stream.sorted instead)
        2. implement hashcode, equals and ~~toString~~
    7. ~~Get all products created after a certain date~~
        1. ~~use dateCreated.after(Date.now()) /.before to check wether date objects differ~~
    8. ~~Get all products modified after being created( dateCreated != dateLastModified)~~

2. class Product
    1. Fields
        1. ~~id(unique)~~
           ~~Use UUID.randomUUID()~~
        2. ~~name~~
        3. ~~category (enum of categories)~~
           Arrays.toString()
        4. rating(1-10)
           ~~Use enum here too?~~
        5. ~~date created~~
           new Date(); .after / before
        6. ~~date last modified~~

    2. Has to be immutable.
        1. https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html

### Extra

Extend functionality in warehouse for:

* get all products that have 1 category linked
* get all products in a category
* get a Map that has initial letters as key and amount of products as value
* get all products with max rating, created this month, and sorted by date descending.