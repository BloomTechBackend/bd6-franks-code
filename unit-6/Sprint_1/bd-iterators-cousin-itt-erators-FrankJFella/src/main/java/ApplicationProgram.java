import com.frank.addams.AddamsFamilyApplication;

import java.io.FileNotFoundException;
/********************************************************************************************
 * Application manager
 *
 * This is the application program because it has the main()
 ********************************************************************************************/
public class ApplicationProgram {
        public static void main(String args[]) throws FileNotFoundException {
                // instantiate an instance of the application to be run
                AddamsFamilyApplication anAddamsFamilyApplication = new AddamsFamilyApplication();

                // invoke the application controller to start the application
                anAddamsFamilyApplication.run();
        }
}
