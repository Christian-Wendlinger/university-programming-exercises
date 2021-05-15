import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class FileTape {

  private Scanner in;
  private PrintStream out;
  private File file;

  public FileTape(String name) {
    this.file = new File(
        "eiditape" + name + ".txt");
    // Create empty file
    try {
      this.file.createNewFile();
    } catch (IOException e) {
      System.err.println("Could not create file "
          + this.file.getAbsolutePath());
    }
  }

  private Scanner createNewScanner() {
    try {
      return new Scanner(new BufferedReader(
          new FileReader(this.file)));
    } catch (FileNotFoundException e) {
      System.err.println(
          "File " + this.file.getAbsolutePath()
              + " not found.");
    }
    return null;
  }

  public void rewindAndRead() {
    // first close and flush output stream
    if (this.out != null) {
      this.out.close();
      this.out = null;
    }
    if (this.in != null)
      this.in.close();
    this.in = createNewScanner();
  }

  public void rewindAndWrite() {
    // first close input stream
    if (this.in != null) {
      this.in.close();
      this.in = null;
    }
    if (this.out != null)
      this.out.close();
    try {
      this.file.delete();
      this.file.createNewFile();
      this.out = new PrintStream(this.file);
    } catch (FileNotFoundException e) {
      System.err.println(
          "File " + this.file.getAbsolutePath()
              + " not found.");
    } catch (IOException e) {
      System.err.println("Could not create file "
          + this.file.getAbsolutePath());
    }
  }

  public String next() {
    if (this.in != null && this.in.hasNext())
      return this.in.next();
    return null;
  }

  public boolean hasNext() {
    return in != null && in.hasNext();
  }

  public void write(String s) {
    if (this.out != null)
      this.out.println(s);
  }

  public String toString() {
    if (file != null)
      return file.getAbsolutePath();
    return "???";
  }
}
