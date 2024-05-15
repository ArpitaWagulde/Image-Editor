# DESIGN

We have implemented a Model-View-Controller Design for this Application.

## MODEL

ImageProcessingModel

* This is the Interface that is implemented by the Model class of the Application. This represents
  the older model with lesser functionalities offered.

ImageProcessingMIMEModel

* This is the Interface that is implemented by the new Model class of the Application also known as
  MIME(More Image Manipulation and Enhancement) Model. This offers more functionalities along with
  the older ones. This interface is a child of ImageProcessingModel which makes interface
  segregation to keep both the models separate.

ImageProcessingGRIMEModel

* This is the interface that is implemented by the new Model class of the Application also known as
  GRIME (Graphical Image Manipulation and Enhancement) Model. This offers all the functionalities a
  graphical user interface offers. This interface is a child of ImageProcessingMIMEModel which makes
  interface segregation to keep all the models separate.

ImageProcessingModelImp

* The Old Model Class is the ImageProcessingModelImp which implements the ImageProcessingModel
  interface.
* An object of the Model class represents one session of the Image Processing Application.
* A user can work with any number of images within a session and the context (variable names) set by
  the user within this session will be maintained by the Model.
* All the filters and manipulations to be performed on an Image, are created as methods within this.

ImageProcessingMIMEModelImp

* The New Model Class is the ImageProcessingMIMEModelImp which implements the
  ImageProcessingMIMEModel interface and extends ImageProcessingModelImp.
* This helps keep both the old and new models separately, so that they can be used separately as per
  user's preference but removes code duplication by extending the older class as all the previous
  functionalities are offered in this model too.
* An object of the Model class represents one session of the Image Processing Application.
* A user can work with any number of images within a session and the context (variable names) set by
  the user within this session will be maintained by the Model.
* All the filters and manipulations to be performed on an Image, are created as methods within this
  where once the image processing is done, it is models responsibility to maintain it within the
  session memory.

ImageProcessingGRIMModelImp

* The new Model Class is the ImageProcessingGRIMModelImp which implements the
  ImageProcessingGRIMEModel interface and extends ImageProcessingMIMEModelImp.
* This helps keep all the new and old models separate, so that they can be used separately as per
  user's preference but removes code duplication by extending the older class as all the previous
  functionalities are offered in this model too.
* An object of the Model class represents one session of the Graphical Image Processing Application.
* The methods required to render the images to the view in the required format are implemented here.

Image

* We also have a class to represent an Image.
* This Image class currently stores the RGB Channel data in the form of a Hashmap of a 2-D ArrayList
  of Integer values.
* Usage of Hashmap allows us to scale this class to incorporate different and additional channels in
  the future that may be required to represent pixel values of an Image.
* All operations that are performed on a single image are implemented here.

Component

* This is an ENUM to capture the different possible channels for storing Image Data. Using this
  allows us to easily add more channels which can directly be picked up by the Model for processing.

## CONTROLLER

ImageProcessingController

* This is the Interface that is implemented by the controller classes of the Application. It offers
  one method to start the session.

ImageProcessingControllerImp

* The Controller class takes in an instance of the old Model and calls the relevant methods
  depending
  upon user input on the CLI.
* The input and output streams for the controller are modularized so that it can in future couple
  with different Views.

ImageIOController

* This class is part of the controller and performs all the input/output operation on an image.
* This keeps the model separate and unaware of the image it is operating on. For the model, Image is
  just the data in which it is represented in.

ImageProcessingMIMEControllerImp

* The Controller class takes in an instance of the new MIME Model.
* This controller uses Command Design Pattern and uses the relevant instance of
  ImageProcessingCommand depending upon user input on the CLI.
* The input and output streams for the controller are modularized so that it can in future couple
  with different Views.

ImageProcessingCommand

* This is an interface which represents all the commands used by Image Processor.
* It is used to implement the command design pattern and all the classes implementing this interface
  are present in commands package of controller.

ImageProcessingGRIMEController

* This is the Interface that is implemented by the controller class of the Graphical User Interface
  Application. It offers methods to be called from the view to keep the session running.
* This interface extends the older controller ImageProcessingController, this helps us to offer all
  the types of functionalities offered (CLI and GUI) through a single controller.

ImageProcessingGRIMEControllerImp

* This class is the new controller and is a child class of older controller
  ImageProcessingMIMEControllerImp. This allows us to use a single controller for all the
  functionalities offered.
* A user can work on a single image that is being displayed to them and the context (variable names)
  required by the commands will be created and maintained by the controller. The user is unaware of
  how their image is stored in session memory and with what name unlike in Command Line Interface
  where user had to specify the context/variable name.
* The view calls the methods of the controller and controller calls the methods of the model to do
  the operation and then refreshes the view. This design pattern is called as Command Callback. It
  also handles the exceptions and they should be displayed to the user.

### commands

Commands package to hold all the command classes required to run the commands of an Image Processor.
Below is the list classes included in this package.

* BlueComponent
* Blur
* Brighten
* ColorCorrect
* Compress
* GreenComponent
* Histogram
* HorizontalFlip
* IntensityComponent
* LevelsAdjust
* Load
* LumaComponent
* RedComponent
* RGBCombine
* RGBSplit
* Save
* Sepia
* Sharpen
* ValueComponent
* VerticalFlip

## VIEW

ViewInterface

* This interface represents all the methods a view should have for Graphical Image Manipulation and
  Enhancements.

GUIView

* This class represents a Graphical User Interface for an ImageProcessing application. This class
  uses Java Swing to create and display the UI.

## Main

* This is the Main class and the entry point for the application.
* Running the Main method will setup the latest Model, Controller and view instances and will allow
  the user to interact with the Application via Graphical User Interface or Script file or by
  running the commands in an interactive shell.
* Controller and model are launched to accept commands through interactive command-line option if
  user mentions '-text'.
* If a script file is passed as an argument, then the program runs the script and exits.
* By default, graphical user interface is launched when the program runs.

# Other

## CITATION FOR IMAGE:

The image(Sample.png) and all its processed variants are generated and owned by us (Rajiv Shah
and Arpita Wagulde) and we here by authorize the use of these images for the purpose of testing and
grading our solution.
