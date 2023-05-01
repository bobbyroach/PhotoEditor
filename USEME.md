# Use me: 

### Layers:
#### The top of the program shows a radio buttom displaying the first 20 layers in a project. As layers are added or removed, the radio buttons visible will reflect those changes. Intiallaly, since there are no layers, there are no buttons in this section.

### String script:
#### The left side has a text box. This is where you can enter a string version of commands.
    Click run text to execute the words entered. Note, you do not need to input a complete 
    script. You can make a new project, click run text, then enter the commands to add a layer, 
    then click run text again.
### Menu:
* The following two commands can only be called once. You must choose 1/2, not both.
#### new-project project-height project-width project-name
    new-project P1 400 200
#### load-project file-path 
    load-project projects/P1.collage
* the following commands can be called once a project has been made (ie the two above commands)
#### add-layer layer-name
    add-layer layerOne
#### clear-layer layer-name
    clear-layer layerOne
#### remove-layer layer-name
    remove-layer layerOne
#### save-image path 
    save-image image/subDirName/image.ppm
#### save-project project-path
    save-project projects/cat.collage
#### add-image-to-layer layer-name file-path offsetX offsetY
    add-image-to-layer layer1 C:/user/home/image/a4image.ppm 0 3
#### set-filter [darken-value] [darken-intensity] [darken-luma] [brighten-value] [brighten-intensity] [brighten-luma] [red-component] [green-component] [blue-component] [darken-multiply] [brighten-screen] [difference] layer-name
    set-filter darken-value layer34
    set-filter red-component layer34

### Canvas:
#### The middle of the screen displays the complied project, with the layer name all the way to the left in the radio buttons being the bottom layer.

### Buttons at the bottom:
- To be user friendly, some buttons are only available at certian points throughout the 
  project's lifetime
#### Here are the visible buttons at each stage in the project
1. Upon Opening
   1. new-project
   2. load-project
2. After one of these are executed SUCCESFULLY
   1. add-layer
3. After a layer is added
    1. add-layer
   2. add-image
   3. remove layer
4. After an image is added
   1. add-layer
   2. add-image
   3. remove layer
   4. Clear layer
   5. save image
   6. save project

#### New Project
    takes you through the steps to execute new-project
#### Save Project
    takes you through the steps to execute save-project
#### Load Project
     takes you through the steps to execute load-project
#### Add Layer
    takes you through the steps to execute add-layer
#### Save Image
    takes you through the steps to execute save-image
#### Add Image
    takes you through the steps to execute add-image
#### Clear Layer
    takes you through the steps to execute clear-layer
#### Remove Layer
    takes you through the steps to execute remove-layer
