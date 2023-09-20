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

* ~~get all categories that have 1 products linked~~
    * use streams to filter on product category and return them as a list
* ~~get all products in a category~~
    * Create function to check enum if an input string is a part of it, have it return boolean
      implement that function in a method on Warehouse to return amount of products in the given enum
      If it returns false, return no such category.
    * Map categories with .count() of products in that category
* ~~get a Map that has initial letters as key and amount of products as value~~
    * Map on stream
    * ~~getName().substring(0,1) as key~~
    * ~~forEach + collect med Product::getname som metodreferens~~
* ~~get all products with max rating, created this month, and sorted by date descending.~~

Refactoring

* Ensure Products being immutable
* Refactor Warehouse functions
    * If possible, simplify to enhance readability
    * Extract methods on recurring parts of code
* Refactor Test Code
    * Extract methods on recurring parts of code
    * Set up @BeforeAll /@AfterAll on repetetive tasks
* Reformat and cleanup redundant lines