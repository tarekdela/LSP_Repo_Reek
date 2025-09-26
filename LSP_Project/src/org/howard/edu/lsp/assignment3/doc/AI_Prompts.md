# AI Prompts Used in Assignment 3

This document chronicles the AI assistance received during the development of Assignment 3, including the prompts used and how the responses were applied to the project.

## Prompt 1: Initial Task Understanding

**My Prompt:**
> "**The Task **
> 1. Start with your Assignment 2 solution (ETLpipeline.java).  
> 2. Brainstorm ways to make it more object-oriented.  
> 3. Redesign your solution into multiple classes with clearer responsibilities.  
> 4. Your program must still meet the exact same requirements as Assignment 2(ETLPipeline.java): same inputs, same outputs, same transformations, same error handling, and same relative paths."

**AI Response Summary:**
The AI suggested breaking down the monolithic ETLPipeline into 6 separate classes:
- Product (data model)
- DataExtractor (CSV reading)
- ProductTransformer (business logic)
- DataLoader (CSV writing)
- ETLPipelineStats (statistics tracking)
- ETLPipeline (main orchestrator)

The AI applied object-oriented principles like Single Responsibility Principle, encapsulation, and composition.

**How I Used It:**
I adopted the suggested class structure as it clearly separated concerns and made the code more maintainable. Each class now has a single, well-defined responsibility.

## Prompt 2: Package Structure Clarification

**My Prompt:**
> "It should be recognized as a part of assignment3, assignment 2 should remain untouched. This is a completely new file."

**AI Response Summary:**
The AI updated all package declarations from `org.howard.edu.lsp.assignment2` to `org.howard.edu.lsp.assignment3` and clarified that this would be a completely separate implementation while keeping Assignment 2 intact.

**How I Used It:**
This ensured proper organization of my project structure, keeping assignments separate while allowing both to coexist in the same project.

## Prompt 3: File Structure Requirements

**My Prompt:**
> "Hold on, I will give you an example of how the fiile tree should look . 
> project-root/ ├── src/ │   └── org/ │       └── howard/ │           └── edu/ │               └── lsp/ │                   └── assignment3/ │                       ├── <YourClass1>.java │                       ├── <YourClass2>.java │                       ├── <YourClass3>.java │                       └── doc/ │                           ├── REFLECTION.md │                           └── AI_PROMPTS.md ├── data/ │   ├── products.csv │   └── transformed_products.csv"

**AI Response Summary:**
The AI acknowledged the specific file structure requirements and updated the code comments to reflect the exact directory structure needed, including the doc/ folder for documentation files.

**How I Used It:**
I followed this exact structure when creating my project files, ensuring compliance with the assignment requirements.

## Prompt 4: Deliverables and Javadoc Requirements

**My Prompt:**
> "These are deliverables
> 1. Source Code for Assignment 3:    - Must be under the package org.howard.edu.lsp.assignment3.    - One public class per file.    - Include Javadocs for each class and all public methods. (You may use your AI assistant to help generate Javadocs, but you must review and edit them for accuracy.)"

**AI Response Summary:**
The AI completely rewrote the code to include comprehensive Javadoc comments for:
- All classes with purpose, author, and version information
- All public methods with @param, @return, and @throws documentation
- Proper formatting following Javadoc conventions

**How I Used It:**
I used the generated Javadocs as a starting point but reviewed and edited them for accuracy. I replaced placeholder "[Your Name]" with my actual name and verified that all descriptions accurately reflected the code's functionality.


## Overall AI Contribution

The AI assistance was instrumental in:
1. **Architectural Design**: Suggesting a clean separation of concerns through multiple classes
2. **Code Organization**: Ensuring proper package structure and file organization
3. **Documentation**: Generating comprehensive Javadocs that I then reviewed and refined
4. **Implementation Guidance**: Providing step-by-step instructions for project setup


All AI-generated code and suggestions were carefully reviewed, tested, and modified as needed to ensure they met the assignment requirements and functioned correctly.