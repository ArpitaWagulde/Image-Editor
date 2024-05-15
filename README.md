# DESIGN

We have implemented a Model-Controller Design for this Application.

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

# Change Log

## Change log of Assignment 5

* Changed README for better readability
* Abstracted code written for flipHorizontally and flipVertically
* Fixed Exception Handling issue in Save JPG/PNG
* Added an interface for the controller named ImageProcessingController and controller for
  Assignment 4 is ImageProcessingControllerImp.
* Created a new interface and a new class for the functionalities to be offered in the Assignment 5.
  This will retain the Assignment 4 application as is and allow us a new application of Assignment 5
  with all the functionalities offered in Assignment 4 included in it.
* Used interfaces instead of using concrete classes while defining objects.
* test.imageprocessingtest.ImageProcessingControllerTest was using Junit 3 for testing where class
  extends TestCase. It has now been replaced with call to static methods of asserts as part of
  Junit4.
* A controller was catching and then throwing the same exception in a catch block. But that try
  block will never throw any exception. Removed that redundant exception throwing and try-catch
  block.
* Operations on a single image are now done in Image class.
* ImageData is made private.
* Input/Output operations of an image will now be done in ImageIOController - a new class which is
  part of the controller.
* A new ImageProcessingMIMEControllerImp class is created which is now a new controller for
  Assignment 5. This will help us keep two applications separately, one for Assignment 4 and another
  for Assignment 5.
* Main method is now modified to take an argument 'Old' if the user wants to use the Assignment 4
  model. If no argument is passed, Assignment 5 model will be called by default.
* Modified/Wrote tests to accommodate all the above design changes.
* Implemented Command Design Pattern.
* Created test packages separate for both the applications.

## Change log of Assignment 6

* Created a new interface and a new class for the functionalities to be offered in the Assignment 6.
  These were required to support the graphical user interface. The new interface is called
  ImageProcessingGRIMEModel and the class is called ImageProcessingGRIMEModelImp.
* The new model classes contain methods required to render the images in required formats to be sent
  to the view to be displayed through the controller.
* Similarly, to support the graphical user interface a new controller interface and class is
  created. They are called ImageProcessingGRIMEController and ImageProcessingGRIMEControllerImp
  respectively.
* Updated Main class and main method to now support the new requirements of graphical user
  interface, interactive command line and running the script directly.
* ImageIOController was not throwing an exception if user tries to save the image of unsupported
  file format. Changed that so that user now will be notified of the same.
* A method used to create hashmap of objects required for Command Design Pattern was of return type
  void. Changed that to return the map so that now the same method can be used in new controller to
  create the map with one additional command to be supported.
* The ImageProcessingCommand classes earlier required appendable to add an error message to it.
  Changed that so that now the classes send the error message ahead to the controller as an
  exception. And then the controller will either display the message to user through view or add the
  error message to appendable to be displayed on the command line.
* The ImageProcessingCommand classes were sending success messages to the user. Removed that so that
  now only errors will be thrown.
* Changed access modifier of a method in Image class to private as it was needed internally only.
* The preview method of ImageProcessingMIMEModelImp is now package private instead of private so
  that new model class ImageProcessingGRIMEModelImp can reuse it.
* Added a test to check whether the run script command actually parses the commands as in the given
  format in the controller test.
* ImageProcessingCommand Levels Adjust was not throwing an exception if no level adjusting
  parameters are passed. Fixed that to throw an exception, if this occurs.
* Added new tests and changed older tests to accommodate these new changes