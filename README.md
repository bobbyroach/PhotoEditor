

# All work is done by Robert Roach and Daniel Kamen


# Description

This program acts as a collage photo editor. There are many features that allow the 
user make the picture collages they want. The user can add as many images as they want,
set filters on certain layers, add and manipulate certain layers, and save projects and 
images. There are three main parts of the code. The model, controller, and the view. 

The model is the representation 
of the photo college and stores all its objects, like the images. It's also 
responsible for handling all the possible commands and functionality, such as
applying a filter to an image.

The controller is what deals with user input. There are multiple ways to give 
the program user input, however, all user input is more or less handled the same way.
For entering text through a script or on the command line, the controller receives 
the input as strings (messages) and then sends commands to the model to change it there.
When interacting with the GUI view, pressing the buttons that represents their
respective commands will send that user input as strings to the model and change it, 
similar to just entering text.

The view is what the user will see and be able to interact with. For text commands, 
all that will appear is messages that are displayed when something happens, 
such as an error occurs when loading a photo. In the GUI view, pressing the buttons 
and interacting it will send commands to the controller, where it updates the model. Then
the controller sends commands back to the GUI, and it updates accordingly.

Here is a breakdown of the program's functionality:
- Starting the program: 
  - To start editing, the user must either create a new project or load an already existing
project
- Once the project has been started/loaded:
  - The user must choose to add a layer and name it to be able to add photos
- After adding a layer, and before adding any images, the user has the option to:
  - Add another layer
  - Add an image and then select the image from the file explorer or give the images file path
  - Remove a specified layer
- After adding an image, the user can do any of the following:
  - Add another layer
  - Add another image
  - Remove a specified layer
  - Clear a specified layer
  - Save the project
  - Save the current composite image
  - Apply any filter




# Requirements/Dependencies

- Java 11 or a higher JRE
- JUnit 4 for running tests


## UseMe

The UseMe can be found in this directory in the file named USEME.md
Here is a list of instructions that can be used on the command line to run the program:

"new-project 500 500 Protect add-layer l1 add-image-to-layer l1 a4Image.ppm 0 0 " +
"add-layer l2  set-filter darken-value l1 add-image-to-layer l2 a4Image.ppm 0 0 " +
"set-filter brighten-intensity l2 save-image res/TACommand.ppm quit";

"new-project 500 500 P1" +
" add-layer layer1 add-image-to-layer layer1 a4Image.ppm 0 0" +
" set-filter darken-luma layer1" +
" save-image res/darkenLuma.ppm " +
"quit"

"new-project 500 500 P1" +
" add-layer layer1 add-image-to-layer layer1 a4Image.ppm 0 0" +
" set-filter brighten-intensity layer1" +
" save-image res/brightenIntensity.ppm " +
"quit"

"new-project 500 500 P1" +
" add-layer layer1 add-image-to-layer layer1 a4Image.ppm 0 0" +
" set-filter brighten-luma layer1" +
" save-image res/brightenLuma.ppm " +
"quit"


# Code Design:

# Model:
in src/model package


Our image interface implements all the necessary methods for our 
image model and allows for other types of image models to also implement it. 
The image model is a representation of an image collage application 
and handles all the changes and functions a person can call/use to edit an image. 

Our model has two interfaces and one abstract class: 
- Image - This contains all the main functions for a photo application model, including 
saving files, loading projects, starting projects, applying filters, and adding pictures/layers
- ImageModelInterface - This interface contains all our model's helper functions, setter
functions, and getter functions. 
- ImageModel - This is an abstract class for any image model implementation and contains 
all the setter, getter, and helper methods any implementation of a model would need

Our image model has 8 fields : 
- Height - which is the height of the project specified by the user
- Width - which is the width of the project specified by the user
- 2D ArrayList of Pixel - which represents the current layer's pixels
- Linked Hashmap - which stores all the created layers and their respective names in the order in which they were added
- Project name - which keeps track of the current project's name
- Hashmap<int, Layer> - this stores the position of every layer in order to keep track of where each layer is
- MaxValue - An integer that represents the maximum value in a layer of rgb pixels
- Filter - A FilterPPM class object that gives the model access to different filter functions/commands

Here is a list of the interface methods and what they do :


  **StartProject** : 
  - This method is called when creating a new project, and it takes in a name for the project, a height, and a width and initializes those fields in the image model

  **AddLayer** : 
  - This method asks for a name for an input and creates a layer with the given name. 
  - If a project hasn’t been started, the method will throw an IllegalStateException and if the layer name already exists, it will throw an IllegalArgumentException
  - The layer is set to have all white, transparent pixels by default and each layer is stores into a linked HashMap to be accessed later

  **AddImageToLayer** : 
  - This method takes a layer name, file name, x coordinate, and y coordinate as inputs.
  - If project hasn’t been started, throws an IllegalStateException and if the layer name or file name given doesn’t exist, throws and IllegalArgumentException
  - This method reads the pixels from the image provided and replaces the pixels on the current layer with the pixels from the image starting 
        from the given coordinates where the new image's top left corner will be
        
  **SaveProject** : 
  -  Throws an IllegalStateException if project hasn't been started
  -  This method creates a new file for the current project to be stored and is stored as text in the following format : 
       ProjectName
       Width Height
       Max-value
       Layer-name filter-name
       Layer-Content-Format
       ...
       Layer-name filter-name
       Layer-Content-Format
  - Where Layer-Content-Format is all the image's/layer's pixels printed out

  **LoadProject** : 
  - Throws an IllegalArgumentException if project file doesn't exist and an IllegalStateException if project has already been started
  - Loads the given project, reads the pixels of every layer and initializes the LinkedHashmap to store all the layers in the project and the project name

  **SaveImage** : 
  - Throws an IllegalStateException if project hasn't been started
  - Goes through every layer in the current project and combines all the pixels into one image. This is done using math to create a new pixel from the background pixel and the current new image's pixel being added to the background. Once these new pixels are created, they become the background pixel for the next layer's pixels. This process repeats until it goes through every layer stored and then saves the resulting image at the specified file path
       

  **MakeWhiteBackground** : 
  - This method simply creates and returns a 2d ArrayList of all white, transparent pixels to be given to new layers when they're created

  **Only-** : 
  ***red***
  ***blue***
  ***green***
  - This method asks for a layer name
  - If project hasn’t been started, throws an IllegalStateException and if the layer name given doesn’t exist, throws and IllegalArgumentException
  - This method then changes the image's pixels to only contain values of the color specified on the specified layer


  **Brighten and Darken** : 
  - This is an unbrella for brighten/darken luma, value, intensity
  - These methods ask for a layer nam. 
  - If project hasn’t been started, throws an IllegalStateException and if the layer name given doesn’t exist, throws and IllegalArgumentException
  - A brightenType/DarkenType can be one of three : 
    - value : all values of a pixel are increased by the highest pixel value
    - intensity : all values of a pixel are increased by the average pixel value
    - luma : all values of a pixel are increased by this formula : (0.2126 * r) + (0.7152 * g) + (0.0722 * b)
    
    ****The Blending filters****
    
    ***Brighten-screen***
    - brightens the layer selected based off of the compiled imaged below that layer
    
    ***Darken-multiply***
    - darkens the layer selected based off of the compiled imaged below that layer
    
    ***Difference***
    - This blending filter takes two pixels RGB components and subtracts them component-wise. Let (r,g,b) be the RGB component of a pixel the current layer and (dr, dg, db) be the RGB component of the composite image below. Then the new RGB of the current layer (before composing the alphas) is (r', g', b') where r' = abs(r - dr).
    


  **RemoveFilter** : 
  - If project hasn't been started, throws an IllegalStateException and if the layer name given doesn’t exist, throws and IllegalArgumentException
  - This method removes a filter for a given layer
  
  **RemoveLayer** : 
  - If project hasn't been started, throws an IllegalStateException and if the layer name given doesn’t exist, throws and IllegalArgumentException
  - This method removes a layer from the project and any data associated with it (thank you java garbage collector)
  
  **ClearLayer** : 
  - If project hasn't been started, throws an IllegalStateException and if the layer name given doesn’t exist, throws and IllegalArgumentException
  - This method removes any chnages made to a layer, and resets it to be identical to a brand new, blank layer, but maintaining its position in the 'stack' of layers.

  **GetHeight and GetWidth** : 
  - Returns the project's height/width

  **SetHeight and SetWidth** : 
  - Sets the project's height/width

  **GetPixelByCoord** : 
  - This method takes in an x and a y value (integers), and returns the pixel at the given coordinate






## Other classes/interfaces in the model and their purpose(s) :
  
**Class Layer** : 
- LayerInterface - represents any kind of layer and the possible functionality to change layers
- Layer - This class is responsible for storing all values associated with each created layer. 
It holds the name of its filter, its current pixel, 
whether those pixels have a filter on them or not, and its raw pixels, 
which are not affected by filters and only changed by adding images to the layer.
  

**Pixels** : 
- Pixel interface - representation of any type of pixel
- RGBPixel interface - representation for any type of rgb pixel and its functionality
- RGBPixel - This class is responsible for storing the rgba values for every pixel. 
It also handles a lot of Pixel arithmetic that's used to calculate new 
pixels after they have been changed by a filter or altered when adding an image 
or running the saveImage method in the model.
- HSLPixel interface - representation for any type of HSL pixel and its functionality
- HSLPixel - This class represents an HSLPixel implementation and is used to store the HSL 
values of this pixel. It handles the arithmetic to change pixels appropriately and even
convert to other types of pixels.


**Filters:**
- Filters class and interface that allows for an easier representation of
  any filters applied to layers


  
  
  
  
  
  
  


# Controller :
In src/controller package.

The controller handles how a user would input commands and run our image collage application. 

The ImageController interface represents any version of an image controller and contains the 
methods that any implementation would need.


The AbstractImageController contains methods and functionality that all current 
controller implementations use. It contains the following fields: 
- ImageModelInterface - the model to be controlled by this controller
- AbstractCommand - The command that gets initialized when a command is entered
- Boolean hasStartedEditing - keeps track of whether the project has started
- Boolean hasJustSaved - keeps track of if the last command was a save command
- setFilterCommands - a hashmap of filter commands that the controller can use
- PrimaryCommands - a hashmap of primary commands that the controller can use, starting
and/or loading a project
- SecondaryCommands - a hashmap of secondary commands that the controller can use, such as
saving an image or project, setting a filter, or adding an image or layer
- Readable in - a readable object that the controller can use to read files
- GUIView - the view that the controller can manipulate


There are two implementations of the controller interface:

## ImageControllerImpl

This controller takes in commands from the command line to run the code. 
Here are four different scripts of commands that will create a new project, 
add at least one image as a layer, add some layers and filters on it and 
save the resulting image and project in other files.
       
### functionality :

When a command is typed, the controller (inside the runCommand() method), 
reads whether the command line argument is an integer or a string. 
Its initial command is parsed in, and based off of that, sub commands are read/run. 

## ImageControllerGUI

This controller implementation is responsible for taking in inputs from the 
view and then manipulating it accordingly. The view sends string commands when an action
is performed and then reads the string and depending on what it reads, it calls functions
in the view to change it.

Methods:
- RunImage - This method isn't used in this implementation of a controller interface
- RunCommand - This function receives a string of commands and then calls functions
in the view that change it accordingly
- Accept - This method receives the string of commands from the view and also
refreshes the image of the GUI every time it's called
       
       
# View
in src/view package

The View Interface - Interface responsible for the visual representation of the model.
We have two implementations of this interface; ImageView and SwingGuiView

### ImageView


This class only takes in a model and an appendable. It's responsible for 
displaying messages to the user by appending any error message to the 
output appendable. Mostly this displays the messages from exceptions.

### ImageGuiView

- GUI view class that creates a graphical interface for the user to
  interact with. This class then sends those messages to the controller which
  manipulates the model. After that, the controller communicates those changes and 
changes the view accordingly
- PhotoEditor - main class that runs the GUI, located outside the controller, model
and view packages in the src

  

# Citation for Photo :
The image used for this project, file titled a4Image.ppm in the src/image folder, is a Gettys Image which is free to use as a stock photo. 
https://pixabay.com/photos/street-lights-bridge-london-7705265/ 
