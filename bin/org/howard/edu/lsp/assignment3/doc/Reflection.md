# Assignment 3 Reflection

I started off this assignment with the base file of assignment 2, ETLPipeline. Really all I had to do was to place the document in Claude AI and tell it some of the instructions for assignment 3.

We started by learning about the desired file structure and what was being asked for. Assignment 3 asked for:
1. Start with your Assignment 2 solution
2. Use a generative AI assistant to brainstorm ways to make it more object-oriented
3. Redesign your solution into multiple classes with clearer responsibilities
4. Your program must still meet the exact same requirements as Assignment 2

Claude AI and I went through these one by one, making sure every step worked along the way.

## What's Different About the Design?

The biggest change is breaking everything apart. Assignment 2 had one massive class doing everything - reading files, transforming data, writing output, and tracking stats. It was about 150 lines all crammed together.

Assignment 3 splits this into six separate classes. Now we have `Product` for the data model, `DataExtractor` for reading CSV files, `ProductTransformer` for the business logic, `DataLoader` for writing files, `ETLPipelineStats` for tracking numbers, and the main `ETLPipeline` class that coordinates everything.

## How is it More Object-Oriented?

The new design actually follows object-oriented principles instead of just throwing everything in one place. Each class has one job - the `DataExtractor` only worries about reading files, the `ProductTransformer` only handles the discount and name changes, etc. This is way better than having one class try to do everything.

I used **encapsulation** properly this time with the `Product` class - all the fields are private and you access them through getters and setters. The old version just had everything floating around as variables in methods.

The main class now uses **composition** - it creates instances of the other classes and uses them together. This makes it easier to test and change individual pieces without breaking everything else.

## Which OO Concepts Did I Use?

- **Classes and Objects**: Obviously, six classes instead of one, with actual objects being created
- **Encapsulation**: The `Product` class hides its data behind private fields and public methods
- **Abstraction**: Each class has a clean interface - you don't need to know how CSV parsing works to use `DataExtractor`
- **Polymorphism**: Used method overriding for the `toString()` method in `Product`

I didn't really use inheritance because it didn't make sense here. The classes work together but they're not really "types" of each other.

## Testing That It Still Works

I ran both versions with the same input file and compared the outputs - they're identical. Same transformations, same CSV format, same everything. I also tested the edge cases like empty files and missing files, and both versions handle them exactly the same way.

The console output is the same too, including all the debug messages and summary stats. The only difference is the internal code structure - from the outside, they work identically.

## Conclusion

This assignment showed me how much cleaner code can be when you actually follow object-oriented principles. Instead of one confusing class, I now have six focused classes that are way easier to understand and modify. The functionality didn't change at all, but the code is much better organized.