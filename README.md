

You can run the code by replacing the products document with the information that needs to be transformed then running it in Eclipse.

The transformed___.csv document will update. 


I used Claude to help me with nuances of Eclipse and java.

One of my AI prompts (debugging, looking for transformed file):

ME: What does this mean:  Console output, "contained inside ETLPipeline.java, "private static final String OUTPUT_FILE = "data/transformed_products.csv""

AI: Great! You found that the OUTPUT_FILE variable is correctly set to "data/transformed_products.csv". Since the program reported success but you can't see
the file, let's run the updated version with debug output to see exactly where the file is being created.

This helped me confirm the file was being successfully outputted to, I just needed to find it. 
