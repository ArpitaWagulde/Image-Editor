This document is to summarize how to use the application. There are three ways to use the
application.

* Graphical User Interface
* Run the script provided
* Interactive Command Line Option

## run the jar file

* java -jar Assignment6.jar arg1
* Conditions :
    * The above command should be run from command-prompt/terminal where the jar file is
      present at.
    * java -jar Assignment6.jar -text
        * The interactive command line option will be launched
    * java -jar Assignment6.jar -file name-of-script
        * If a valid file is passed as argument, then the program will run the script on new model
          and exit.
        * A valid file should have 'quit' command inside it.
        * Example : java -jar Assignment6.jar -file testRun
    * java -jar Assignment6.jar
        * Otherwise, by default, the graphical user interface will be launched.

## Interacting with the graphical user interface application

* User must first load an image with the 'Load Image' button. Until an image is loaded, all the
  other buttons are disabled to the user. Once the Load Image button is clicked, a window will pop
  up to select the image file. By default, only supported image file formats can be selected.
* Once an image is loaded, user can perform any operation they want on it.
* To save an image that is being displayed on the window, use the 'Save Image' button. Upon
  clicking, a new window will pop up to save the image where user can select the location to save at
  and the filename to save with. A valid image file format must be used to save or else an error
  will be shown to the user.
* A preview operation is available for all the operations. For operations like Horizontal Flip,
  Vertical Flip, Red Component, Green Component and Blue Component the entire image will be the 100%
  preview image. For rest all operation, by default 50% preview will be shown to the user.
* User can use the slider provided to choose the preview percentage and the preview will dynamically
  change as per the preview percentage.
* A user is required to confirm the operation to change the current image to it. User also has an
  option to cancel the operation, which will revert back the operation and last displayed image will
  be displayed again. If user tries to save the image without confirming the operation, it will be
  prompted to the user that they are to save an image without confirming the operation and if they
  continue to save, then previous image will be saved.
* A user is required to give the input for compress. When the 'Compress Image' button is clicked, a
  slider will be visible to the user on the GUI from where user can select the compression
  percentage.
* Similarly, user inputs are required for levels adjust. When the 'Levels Adjust' button is clicked,
  input boxes will be visible to the user on the GUI from where user can give the black, mid, white
  values as input.

## run the jar file with the provided script file

* Before running the jar with the given script, follow these steps:
    * Navigate to the folder where the jar file is present.
    * (Optional) Add the script provided in this folder.
    * Create a folder named res in that.
    * Add the Sample.png file in this res folder.
* Now the given file can be run by passing the relative path of the script to the jar.
* Example : java -jar Assignment6.jar -file testRun

## Interactive Command Line Option

Which script commands are supported by the application, examples of using them and conditions if
any. Type each command on a new line with all its arguments on the same line.

### load

* load image-path image-name
* Load an image from the specified path and refer it to henceforth by the given image
  name. This will add the image with the given image name in the session memory.
* Conditions : The specified image-path should be valid
* ex : load res/Sample.png Sample

### save

* save image-path image-name
* Save the image with the given name to the specified path.
* Conditions : The image with the given name should be present in the session memory and the
  specified image-path should be valid and should include the name of the file.
* ex : save res/Sample.png Sample

### red-component

* red-component image-name dest-image-name
* Create an image with the red-component of the image with the given name, and refer to it
  henceforth by the given destination name.
* Conditions : The image with the given name should be present in the session memory.
* ex : red-component Sample redSample

### green-component

* green-component image-name dest-image-name
* Create an image with the green-component of the image with the given name, and refer to it
  henceforth by the given destination name.
* Conditions : The image with the given name should be present in the session memory.
* ex : green-component Sample greenSample

### blue-component

* blue-component image-name dest-image-name
* Create an image with the blue-component of the image with the given name, and refer to it
  henceforth by the given destination name.
* Conditions : The image with the given name should be present in the session memory.
* ex : blue-component Sample blueSample

### value-component

* value-component image-name dest-image-name
    * Create an image with the value-component of the image with the given name, and refer to it
      henceforth by the given destination name. The resultant image will be greyscale images.
    * Conditions : The image with the given name should be present in the session memory.
    * ex : value-component Sample valueSample
* value-component image-name dest-image split p
    * Split preview the value component image for the given percentage of the width and store the
      result in another image with the given name.
    * Conditions : The image with the given name should be present in the session memory and split
      percentage must be between 0 and 100.
    * ex : value-component Sample valueSample split 50

### luma-component

* luma-component image-name dest-image-name
    * Create an image with the luma-component of the image with the given name, and refer to it
      henceforth by the given destination name. The resultant image will be greyscale images.
    * Conditions : The image with the given name should be present in the session memory.
    * ex : luma-component Sample lumaSample
* luma-component image-name dest-image split p
    * Split preview the luma component image for the given percentage of the width and store the
      result in another image with the given name.
    * Conditions : The image with the given name should be present in the session memory and split
      percentage must be between 0 and 100.
    * ex : luma-component Sample lumaSample split 50

### intensity-component

* intensity-component image-name dest-image-name
    * Create an image with the intensity-component of the image with the given name, and refer to it
      henceforth by the given destination name. The resultant image will be greyscale images.
    * Conditions : The image with the given name should be present in the session memory.
    * ex : intensity-component Sample intensitySample
* intensity-component image-name dest-image split p
    * Split preview the intensity component image for the given percentage of the width and store
      the
      result in another image with the given name.
    * Conditions : The image with the given name should be present in the session memory and split
      percentage must be between 0 and 100.
    * ex : intensity-component Sample intensitySample split 50

### horizontal-flip

* horizontal-flip image-name dest-image-name
* Flip an image horizontally to create a new image, referred to henceforth by the given destination
  name.
* Conditions : The image with the given name should be present in the session memory.
* ex : horizontal-flip Sample SampleHFlip

### vertical-flip

* vertical-flip image-name dest-image-name
* Flip an image vertically to create a new image, referred to henceforth by the given destination
  name.
* Conditions : The image with the given name should be present in the session memory.
* ex : vertical-flip SampleHFlip SampleVFlip

### brighten

* brighten increment image-name dest-image-name
* Brighten the image by the given increment to create a new image, referred to henceforth by the
  given destination name. The increment may be positive (brightening) or negative (darkening).
* Conditions : The image with the given name should be present in the session memory.
* ex : brighten 25 Sample brightSample

### rgb-split

* rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue
* Split the given image into three images containing its red, green and blue components
  respectively.
* Conditions : The image with the given name should be present in the session memory.
* ex : rgb-split Sample Sample-red Sample-green Sample-blue

### rgb-combine

* rgb-combine image-name red-image green-image blue-image
* Combine the three images that are individually red, green and blue into a single image that gets
  its red, green and blue components from the three images respectively.
* Conditions : The images with the given names should be present in the session memory.
* ex : rgb-combine SampleCombined Sample-red Sample-green Sample-blue

### blur

* blur image-name dest-image-name
    * Blur the given image and store the result in another image with the given name.
    * Conditions : The image with the given name should be present in the session memory.
    * ex : blur Sample blurSample
* blur image-name dest-image split p
    * Split preview the blur image for the given percentage of the width and store the result in
      another image with the given name.
    * Conditions : The image with the given name should be present in the session memory and split
      percentage must be between 0 and 100.
    * ex : blur Sample blurSample split 50

### sharpen

* sharpen image-name dest-image-name
    * Sharpen the given image and store the result in another image with the given name.
    * Conditions : The image with the given name should be present in the session memory.
    * ex : sharpen Sample sharpSample
* sharpen image-name dest-image split p
    * Split preview the sharpen image for the given percentage of the width and store the result in
      another image with the given name.
    * Conditions : The image with the given name should be present in the session memory and split
      percentage must be between 0 and 100.
    * ex : sharpen Sample sharpSample split 50

### sepia

* sepia image-name dest-image-name
    * Produce a sepia-toned version of the given image and store the result in another image with
      the
      given name.
    * Conditions : The image with the given name should be present in the session memory.
    * ex : sepia Sample sepiaSample
* sepia image-name dest-image split p
    * Split preview the sepia image for the given percentage of the width and store the result in
      another image with the given name.
    * Conditions : The image with the given name should be present in the session memory and split
      percentage must be between 0 and 100.
    * ex : sepia Sample sepiaSample split 50

### compress

* compress percentage image-name dest-image-name
* Compress the given image by the given compression percentage and store the result in another image
  with the given name.
* Conditions : The image with the given name should be present in the session memory and the
  compression percentage must be between 0 and 100.
* ex : compress 90 Sample compressSample90

### histogram

* histogram image-name dest-image-name
* Produces the histogram of the given image and store the result in another image with the given
  name.
* Conditions : The image with the given name should be present in the session memory.
* ex : histogram Sample histSample

### color-correct

* color-correct image-name dest-image-name
    * Color correct an image by aligning the meaningful peaks of the histogram of the given image
      and
      store the result in another image with the given name.
    * Conditions : The image with the given name should be present in the session memory.
    * ex : color-correct Sample ccSample
* color-correct image-name dest-image split p
    * Split preview the color corrected image for the given percentage of the width and store the
      result in
      another image with the given name.
    * Conditions : The image with the given name should be present in the session memory and split
      percentage must be between 0 and 100.
    * ex : color-correct Sample ccSample split 50

### levels-adjust

* levels-adjust b m w image-name dest-image-name
    * Adjust the levels of the given image and store the result in another image with the given
      name.
    * Conditions : The image with the given name should be present in the session memory and b, m, w
      values should be in ascending order and within range of 0 to 255.
    * ex : levels-adjust 60 130 220 Sample laSample
* levels-adjust b m w image-name dest-image split p
    * Split preview the levels adjusted image for the given percentage of the width and store the
      result in
      another image with the given name.
    * Conditions : The image with the given name should be present in the session memory and b, m, w
      values should be in ascending order and within range of 0 to 255 and split percentage must be
      between 0 and 100.
    * ex : levels-adjust 60 130 220 Sample laSample split 50

### run

* run script-file: Load and run the script commands in the specified file.
* Conditions : The script-file should be a valid file with all the commands inside it valid too.
* ex : run res/testRun