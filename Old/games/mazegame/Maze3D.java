/*

        Copyright (c) 1996 James L. Dean

        This program may be distributed or used without payment to its author
   so long as the copyright notices are not altered or removed.

        The author -- James L. Dean -- can be contacted via Email as
   csvcjld@nomvs.lsumc.edu.

*/

import java.awt.*;
import java.applet.*;
import java.util.Date;

public class Maze3D extends Applet
  {
    public  boolean      clearUserAttempts;
    public  boolean      hexagonalRooms;
    private MazeControls mazeControls;
    public  MazeCanvas   mazeCanvas;
    public  TextField    message;
    public  boolean      solutionDisplayed;
    public  double       tilt;
    private Scrollbar    tiltScrollbar;

    public void init()
      {
        solutionDisplayed=false;
        setLayout(new BorderLayout());
        Panel CenterPanel=new Panel();
        hexagonalRooms=false;
        mazeCanvas=new MazeCanvas(this);
        add("Center",mazeCanvas);
        mazeControls=new MazeControls(this);
        add("South",mazeControls);
        tilt=45.0;
        tiltScrollbar=new Scrollbar(Scrollbar.VERTICAL,(int) tilt,5,30,60);
        add("East",tiltScrollbar);
        message=new TextField(117);
        message.setEditable(false);
        add("North",message);
        resize(600,350);
        if (hexagonalRooms)
          message.setText("Use Home, Up Arrow, PgUp, End, Down "
           +"Arrow, or PgDn to solve.");
        else
          message.setText("Use the arrow keys to solve.");
      }

    public void start()
      {
        mazeControls.enable();
        mazeControls.requestFocus();
      }

    public void stop()
      {
        mazeControls.disable();
      }

    public void destroy()
      {
        mazeCanvas.p.stop();
      }

    public boolean handleEvent(
      Event ev)
        {
          boolean handled;
          if (ev.target == tiltScrollbar)
            {
              if (! mazeCanvas.p.alreadyPainting)
                {
                  if ((solutionDisplayed)
                  ||  (mazeCanvas.p.userHasSolved))
                    message.setText("");
                  else
                    if (hexagonalRooms)
                      message.setText("Use Home, Up Arrow, PgUp, End, Down "
                       +"Arrow, or PgDn to solve.");
                    else
                      message.setText("Use the arrow keys to solve.");
                }
              tilt=(double) (90-tiltScrollbar.getValue());
              mazeCanvas.paint(mazeCanvas.getGraphics());
              handled=true;
            }
          else
            handled=false;
          return handled;
        }

    public static void main(String args[])
      {
/*
        If you have AppletFrame.class (available in the Beta 2 release of
        the Javas Developers kit), you can uncomment the next line to have
        this applet run as an application (as well as an applet).
*/
//      AppletFrame.startApplet("Maze3D","3D Mazes",args);
      }
  }

class MazeControls extends Panel
  {
    Checkbox   hexagonalRoomsCheckbox;
    Maze3D     maze3D;
    Checkbox   squareRoomsCheckbox;

    public MazeControls(Maze3D maze3D)
      {
        this.maze3D=maze3D;
        add(new Button("New"));
        add(new Button("Solve"));
        add(new Button("Clear"));
        hexagonalRoomsCheckbox
         =new Checkbox("Hexagonal rooms");
        hexagonalRoomsCheckbox.setState(maze3D.hexagonalRooms);
        add("South",hexagonalRoomsCheckbox);
        squareRoomsCheckbox=new Checkbox("Square rooms");
        squareRoomsCheckbox.setState(! maze3D.hexagonalRooms);
        add("South",squareRoomsCheckbox);
        add(new Button("About"));
      }

    public boolean keyDown(
      Event ev,
      int   key)
        {
          boolean handled;
          if (maze3D.mazeCanvas.p.alreadyPainting)
            handled=false;
          else
            if ((maze3D.solutionDisplayed)
            ||  (maze3D.mazeCanvas.p.userHasSolved))
              {
                maze3D.message.setText("");
                handled=false;
              }
            else
              {
                if (maze3D.hexagonalRooms)
                  maze3D.message.setText("Use Home, Up Arrow, PgUp, End, "
                   +"Down Arrow, or PgDn to solve.");
                else
                  maze3D.message.setText("Use the arrow keys to solve.");
                if (maze3D.hexagonalRooms)
                  switch (key)
                    {
                      case ev.LEFT:
                        handled=true;
                        break;
                      case (int) '4':
                        handled=true;
                        break;
                      case ev.RIGHT:
                        handled=true;
                        break;
                      case (int) '6':
                        handled=true;
                        break;
                      case ev.UP:
                        maze3D.mazeCanvas.p.hexKey(2);
                        handled=true;
                        break;
                      case (int) '8':
                        maze3D.mazeCanvas.p.hexKey(2);
                        handled=true;
                        break;
                      case ev.DOWN:
                        maze3D.mazeCanvas.p.hexKey(3);
                        handled=true;
                        break;
                      case (int) '2':
                        maze3D.mazeCanvas.p.hexKey(3);
                        handled=true;
                        break;
                      case ev.HOME:
                        maze3D.mazeCanvas.p.hexKey(0);
                        handled=true;
                        break;
                      case (int) '7':
                        maze3D.mazeCanvas.p.hexKey(0);
                        handled=true;
                        break;
                      case ev.END:
                        maze3D.mazeCanvas.p.hexKey(1);
                        handled=true;
                        break;
                      case (int) '1':
                        maze3D.mazeCanvas.p.hexKey(1);
                        handled=true;
                        break;
                      case ev.PGUP:
                        maze3D.mazeCanvas.p.hexKey(4);
                        handled=true;
                        break;
                      case (int) '9':
                        maze3D.mazeCanvas.p.hexKey(4);
                        handled=true;
                        break;
                      case ev.PGDN:
                        maze3D.mazeCanvas.p.hexKey(5);
                        handled=true;
                        break;
                      case (int) '3':
                        maze3D.mazeCanvas.p.hexKey(5);
                        handled=true;
                        break;
                      default:
                        handled=false;
                        break;
                    }
                else
                  switch (key)
                    {
                      case ev.LEFT:
                        maze3D.mazeCanvas.p.sqrKey(0);
                        handled=true;
                        break;
                      case (int) '4':
                        maze3D.mazeCanvas.p.sqrKey(0);
                        handled=true;
                        break;
                      case ev.RIGHT:
                        maze3D.mazeCanvas.p.sqrKey(2);
                        handled=true;
                        break;
                      case (int) '6':
                        maze3D.mazeCanvas.p.sqrKey(2);
                        handled=true;
                        break;
                      case ev.UP:
                        maze3D.mazeCanvas.p.sqrKey(3);
                        handled=true;
                        break;
                      case (int) '8':
                        maze3D.mazeCanvas.p.sqrKey(3);
                        handled=true;
                        break;
                      case ev.DOWN:
                        maze3D.mazeCanvas.p.sqrKey(1);
                        handled=true;
                        break;
                      case (int) '2':
                        maze3D.mazeCanvas.p.sqrKey(1);
                        handled=true;
                        break;
                      case ev.HOME:
                        handled=true;
                        break;
                      case (int) '7':
                        handled=true;
                        break;
                      case ev.END:
                        handled=true;
                        break;
                      case (int) '1':
                        handled=true;
                        break;
                      case ev.PGUP:
                        handled=true;
                        break;
                      case (int) '9':
                        handled=true;
                        break;
                      case ev.PGDN:
                        handled=true;
                        break;
                      case (int) '3':
                        handled=true;
                        break;
                      default:
                        handled=false;
                        break;
                    }
                if (maze3D.mazeCanvas.p.userHasSolved)
                  maze3D.message.setText("Congratulations!");
              }
          return handled;
        }

    public boolean action(
      Event ev,
      Object arg)
        {
          boolean handled;
          if (ev.target instanceof Button)
            {
              String label=(String) arg;
              if (label.equals("New"))
                {
                  maze3D.solutionDisplayed=false;
                  maze3D.mazeCanvas.previousHeight=0;
                  maze3D.mazeCanvas.previousWidth=0;
                  maze3D.mazeCanvas.paint(maze3D.mazeCanvas.getGraphics());
                }
              else if (label.equals("Solve"))
                {
                  maze3D.message.setText("");
                  maze3D.solutionDisplayed=true;
                  if (maze3D.mazeCanvas.p.alreadyPainting)
                    maze3D.mazeCanvas.paint(
                     maze3D.mazeCanvas.getGraphics());
                  else
                    if (maze3D.hexagonalRooms)
                      maze3D.mazeCanvas.p.hexDisplaySolution();
                    else
                      maze3D.mazeCanvas.p.sqrDisplaySolution();
                }
              else if (label.equals("Clear"))
                {
                  if (maze3D.hexagonalRooms)
                    maze3D.message.setText("Use Home, Up Arrow, PgUp, End, "
                     +"Down Arrow, or PgDn to solve.");
                  else
                    maze3D.message.setText("Use the arrow keys to solve.");
                  maze3D.solutionDisplayed=false;
                  maze3D.clearUserAttempts=true;
                  maze3D.mazeCanvas.paint(
                   maze3D.mazeCanvas.getGraphics());
                }
              else
                {
                  if (label.equals("About"))
                    maze3D.message.setText(
                     "Copyright (c) 1996 James L. Dean.  This program may be "
                     +"distributed or used without payment to its author.");
                }
              handled=true;
            }
          else if (ev.target == hexagonalRoomsCheckbox)
            {
              squareRoomsCheckbox.setState(false);
              hexagonalRoomsCheckbox.setState(true);
              if (! maze3D.hexagonalRooms)
                {
                  maze3D.hexagonalRooms=true;
                  maze3D.solutionDisplayed=false;
                  maze3D.mazeCanvas.previousHeight=0;
                  maze3D.mazeCanvas.previousWidth=0;
                  maze3D.mazeCanvas.paint(
                   maze3D.mazeCanvas.getGraphics());
                }
              handled=true;
            }
          else if (ev.target == squareRoomsCheckbox)
            {
              squareRoomsCheckbox.setState(true);
              hexagonalRoomsCheckbox.setState(false);
              if (maze3D.hexagonalRooms)
                {
                  maze3D.hexagonalRooms=false;
                  maze3D.solutionDisplayed=false;
                  maze3D.mazeCanvas.previousHeight=0;
                  maze3D.mazeCanvas.previousWidth=0;
                  maze3D.mazeCanvas.paint(
                   maze3D.mazeCanvas.getGraphics());
                }
              handled=true;
            }
          else
            handled=false;
          return handled;
        }
  }

class MazeCanvas extends Canvas
  {
    private boolean     invalidated;
    public  Maze3D      maze3D;
    public  PaintScreen p;
    public  int         previousHeight;
    public  int         previousWidth;
    public  Rectangle   rectangle;
    public  boolean     resize;

    MazeCanvas(Maze3D maze3D)
      {
        this.maze3D=maze3D;
        invalidated=false;
        previousWidth=0;
        previousHeight=0;
        p=new PaintScreen(this);
        p.start();
      }

    public void paint(Graphics g)
      {
        if (invalidated)
          {
            invalidated=false;
            rectangle=bounds();
            if ((previousWidth == rectangle.width)
            &&  (previousHeight == rectangle.height))
              resize=false;
            else
              {
                if (maze3D.hexagonalRooms)
                  maze3D.message.setText("Use Home, Up Arrow, PgUp, End, "
                   +"Down Arrow, or PgDn to solve.");
                else
                  maze3D.message.setText("Use the arrow keys to solve.");
                maze3D.solutionDisplayed=false;
                resize=true;
                previousWidth=rectangle.width;
                previousHeight=rectangle.height;
              }
            p.startOver(true);
          }
        else
          {
            invalidated=true;
            hide();
            show();
          }
      }
  }

class VertexRec
  {
    double x;
    double y;

    VertexRec()
      {
        x=0.0;
        y=0.0;
      }
  }

class StackRec
  {
    short index1;
    short index2;

    StackRec()
      {
        index1=(short) 0;
        index2=(short) 0;
      }
  }

class POINT
  {
    int x;
    int y;

    POINT()
      {
        x=0;
        y=0;
      }
  }

class PaintScreen extends Thread
  {
    static private final int       NUM_COLORS                 = 16;
    static private final int       TOP_COLOR                  = 12;
      /* all but last 3 colors are gray */
    static private final int       RECTANGLE_SE_NW_COLOR      = 10;
    static private final int       TRIANGLE_SSE_NNW_COLOR     =  9;
    static private final int       TRIANGLE_SE_NW_COLOR       =  8;
    static private final int       RECTANGLE_W_E_COLOR        =  7;
    static private final int       FLOOR_COLOR                =  6;
    static private final int       TRIANGLE_SW_NE_COLOR       =  5;
    static private final int       RECTANGLE_SW_NE_COLOR      =  4;
    static private final int       TRIANGLE_SSW_NNE_COLOR     =  3;
    static private final int       BACKOUT_COLOR              = 13;
    static private final int       ADVANCE_COLOR              = 14;
    static private final int       SOLUTION_COLOR             = 15;
    static private final double    RELATIVE_WIDTH_OF_WALL     = 0.25;
      /* relative to side of hexagon or square */
    static private final double    RELATIVE_HEIGHT_OF_WALL    = 2.0;
      /* relative to side of hexagon or square */
    static private final double    MIN_WALL_LENGTH_IN_INCHES  = 0.20;
    static private final double    SECONDS_FOR_MAZE_SELECTION = 0.25;
    static private final int       SUBSTITUTION_HIGH [] =
                  { 4,1,2,8,8,9,9,6,5,7,2,1,2,9,8,8,6,3,5,1,9,5,4,4,9,8,6,
                    0,8,0,6,0,2,4,1,9,2,0,7,4,7,3,0,0,2,6,8,9,4,0,8,3,2,3,
                    2,5,2,4,6,9,7,9,1,3,5,7,1,1,4,5,8,1,6,0,5,7,8,2,3,3,7,
                    3,5,1,7,5,4,0,3,6,3,7,7,1,9,4,0,5,6,6
                  };
    static private final int       SUBSTITUTION_LOW [] =
                  { 1,2,2,1,5,5,4,6,4,6,4,4,5,6,6,3,0,9,6,5,7,2,0,9,3,4,2,
                    3,9,1,9,9,9,3,8,9,3,4,1,5,0,5,2,7,0,8,8,0,4,5,0,3,6,8,
                    1,7,8,8,7,1,3,2,7,7,1,8,0,3,7,5,2,6,4,0,9,9,7,7,4,6,2,
                    0,0,1,7,3,6,6,1,1,2,4,5,9,8,2,8,8,3,5
                  };

    private int        adjacency;
    public  boolean    alreadyPainting;
    private VertexRec  baseRectangle [] [];
    private VertexRec  baseTriangle [] [];
    private boolean    clearUserAttempts;
    private char       computerPage [] [];
    private double     cosTilt;
    private int        counter0;
    private int        counter1;
    private int        counter2;
    private int        counter3;
    private int        counter4;
    private int        counter5;
    private int        counter6;
    private int        counter7;
    private Graphics   graph;
    private boolean    hexagonalRooms;
    private int        hexDeltaX [] [];
    private int        hexDeltaY [] [];
    private int        maxX;
    private int        maxY;
    private MazeCanvas mazeCanvas;
    private boolean    minimized;
    private int        numColumns;
    private int        numRoomsInMaze;
    private int        numRoomsInSolution;
    private int        numRows;
    private boolean    paint;
    private double     pixelsPerX;
    private double     pixelsPerZ;
    public  VertexRec  rectangle [] [];
    private Color      redGreenBlue [];
    private double     relDistOfUserFromScreen;
    private boolean    resize;
    public  boolean    restart;
    private Rectangle  screen;
    private int        seed [];
    private double     sinTilt;
    private boolean    solutionDisplayed;
    private int        sqrDeltaX [] [];
    private int        sqrDeltaY [] [];
    private double     sqrt3;
    private StackRec   stack [];
    public  int        state;
    private double     tilt;
    public  boolean    userHasSolved;
    private char       userPage [] [];
    private int        userX;
    private double     userXRelative;
    private int        userY;
    private double     userYRelative;
    private int        x;
    private double     xMax;
    private double     xOffset;
    private int        y;
    private double     yMax;
    private int        yMod4;
    private double     yOffset;

    PaintScreen(MazeCanvas mazeCanvas)
      {
        int  colorNum;
        int  deltaIndex1a;
        int  deltaIndex1b;
        int  deltaIndex1c;
        int  deltaIndex1d;
        int  deltaIndex1e;
        int  deltaIndex1f;
        int  deltaIndex2;
        int  objectNum;
        int  tint;
        int  vertexNum;

        setDaemon(true);
        this.mazeCanvas=mazeCanvas;
        restart=false;
        hexagonalRooms=this.mazeCanvas.maze3D.hexagonalRooms;
        clearUserAttempts=true;
        state=5;
        baseRectangle=new VertexRec [6] [4];
        for (objectNum=0; objectNum < 6; ++objectNum)
          for (vertexNum=0; vertexNum < 4; ++vertexNum)
            baseRectangle[objectNum][vertexNum]
             =new VertexRec();
        rectangle=new VertexRec [6] [4];
        for (objectNum=0; objectNum < 6; ++objectNum)
          for (vertexNum=0; vertexNum < 4; ++vertexNum)
            rectangle[objectNum][vertexNum]
             =new VertexRec();
        baseTriangle=new VertexRec [4] [3];
        for (objectNum=0; objectNum < 4; ++objectNum)
          for (vertexNum=0; vertexNum < 3; ++vertexNum)
            baseTriangle[objectNum][vertexNum]
             =new VertexRec();
        hexDeltaX=new int [6] [720];
        hexDeltaY=new int [6] [720];
        sqrDeltaX=new int [4] [24];
        sqrDeltaY=new int [4] [24];
        redGreenBlue=new Color [16];
        computerPage=null;
        userPage=null;
        stack=null;
        alreadyPainting=false;
        solutionDisplayed=false;
        userHasSolved=false;
        minimized=false;

        hexDeltaY[0][0]=-1;
        hexDeltaX[0][0]=-2;
        hexDeltaY[1][0]=1;
        hexDeltaX[1][0]=-2;
        hexDeltaY[2][0]=-2;
        hexDeltaX[2][0]=0;
        hexDeltaY[3][0]=2;
        hexDeltaX[3][0]=0;
        hexDeltaY[4][0]=-1;
        hexDeltaX[4][0]=2;
        hexDeltaY[5][0]=1;
        hexDeltaX[5][0]=2;
        deltaIndex2=0;
        for (deltaIndex1a=0; deltaIndex1a < 6;
         ++deltaIndex1a)
          for (deltaIndex1b=0; deltaIndex1b < 6;
           ++deltaIndex1b)
            if (deltaIndex1a != deltaIndex1b)
              for (deltaIndex1c=0; deltaIndex1c < 6; ++deltaIndex1c)
                if ((deltaIndex1a != deltaIndex1c)
                &&  (deltaIndex1b != deltaIndex1c))
                  for (deltaIndex1d=0; deltaIndex1d < 6; ++deltaIndex1d)
                    if ((deltaIndex1a != deltaIndex1d)
                    &&  (deltaIndex1b != deltaIndex1d)
                    &&  (deltaIndex1c != deltaIndex1d))
                      for (deltaIndex1e=0; deltaIndex1e < 6; ++deltaIndex1e)
                        if ((deltaIndex1a != deltaIndex1e)
                        &&  (deltaIndex1b != deltaIndex1e)
                        &&  (deltaIndex1c != deltaIndex1e)
                        &&  (deltaIndex1d != deltaIndex1e))
                          for (deltaIndex1f=0; deltaIndex1f < 6; ++deltaIndex1f)
                            if ((deltaIndex1a != deltaIndex1f)
                            && (deltaIndex1b != deltaIndex1f)
                            && (deltaIndex1c != deltaIndex1f)
                            && (deltaIndex1d != deltaIndex1f)
                            && (deltaIndex1e != deltaIndex1f))
                              {
                                hexDeltaX[deltaIndex1a][deltaIndex2]
                                 =hexDeltaX[0][0];
                                hexDeltaY[deltaIndex1a][deltaIndex2]
                                 =hexDeltaY[0][0];
                                hexDeltaX[deltaIndex1b][deltaIndex2]
                                 =hexDeltaX[1][0];
                                hexDeltaY[deltaIndex1b][deltaIndex2]
                                 =hexDeltaY[1][0];
                                hexDeltaX[deltaIndex1c][deltaIndex2]
                                 =hexDeltaX[2][0];
                                hexDeltaY[deltaIndex1c][deltaIndex2]
                                 =hexDeltaY[2][0];
                                hexDeltaX[deltaIndex1d][deltaIndex2]
                                 =hexDeltaX[3][0];
                                hexDeltaY[deltaIndex1d][deltaIndex2]
                                 =hexDeltaY[3][0];
                                hexDeltaX[deltaIndex1e][deltaIndex2]
                                 =hexDeltaX[4][0];
                                hexDeltaY[deltaIndex1e][deltaIndex2]
                                 =hexDeltaY[4][0];
                                hexDeltaX[deltaIndex1f][deltaIndex2]
                                 =hexDeltaX[5][0];
                                hexDeltaY[deltaIndex1f][deltaIndex2++]
                                 =hexDeltaY[5][0];
                              }
        sqrDeltaY[0][0]=0;
        sqrDeltaX[0][0]=-1;
        sqrDeltaY[1][0]=1;
        sqrDeltaX[1][0]=0;
        sqrDeltaY[2][0]=0;
        sqrDeltaX[2][0]=1;
        sqrDeltaY[3][0]=-1;
        sqrDeltaX[3][0]=0;
        deltaIndex2=0;
        for (deltaIndex1a=0; deltaIndex1a < 4; ++deltaIndex1a)
          for (deltaIndex1b=0; deltaIndex1b < 4; ++deltaIndex1b)
            if (deltaIndex1a != deltaIndex1b)
              for (deltaIndex1c=0; deltaIndex1c < 4; ++deltaIndex1c)
                if ((deltaIndex1a != deltaIndex1c)
                &&  (deltaIndex1b != deltaIndex1c))
                  for (deltaIndex1d=0; deltaIndex1d < 4; ++deltaIndex1d)
                    if ((deltaIndex1a != deltaIndex1d)
                    &&  (deltaIndex1b != deltaIndex1d)
                    &&  (deltaIndex1c != deltaIndex1d))
                      {
                        sqrDeltaX[deltaIndex1a][deltaIndex2]=sqrDeltaX[0][0];
                        sqrDeltaY[deltaIndex1a][deltaIndex2]=sqrDeltaY[0][0];
                        sqrDeltaX[deltaIndex1b][deltaIndex2]=sqrDeltaX[1][0];
                        sqrDeltaY[deltaIndex1b][deltaIndex2]=sqrDeltaY[1][0];
                        sqrDeltaX[deltaIndex1c][deltaIndex2]=sqrDeltaX[2][0];
                        sqrDeltaY[deltaIndex1c][deltaIndex2]=sqrDeltaY[2][0];
                        sqrDeltaX[deltaIndex1d][deltaIndex2]=sqrDeltaX[3][0];
                        sqrDeltaY[deltaIndex1d][deltaIndex2++]=sqrDeltaY[3][0];
                      }
        sqrt3=Math.sqrt(3.0);
        for (colorNum=0; colorNum <= NUM_COLORS-4; ++colorNum)
          {
            /* evenly spaced shades of gray */
            tint=(256*colorNum)/(NUM_COLORS-3);
            redGreenBlue[colorNum]=new Color(tint,tint,tint);
          }
        redGreenBlue[BACKOUT_COLOR]=new Color(255,255,0);
        redGreenBlue[ADVANCE_COLOR]=new Color(0,255,0);
        redGreenBlue[SOLUTION_COLOR]=new Color(255,0,0);
      }

    private void drawQuadrilateral(
      POINT Box [],
      int   colorNum)
        {
          int i;
          int x [];
          int y [];

          x=new int [4];
          y=new int [4];
          for (i=0; i < 4; ++i)
            {
              x[i]=Box[i].x;
              y[i]=Box[i].y;
            }
          graph.setColor(redGreenBlue[colorNum]);
          graph.fillPolygon(x,y,4);
          return;
        }

    private POINT getCorner(
      double    x,
      double    y,
      double    z)
        {
          POINT  corner;
          double xAdjusted;
          double yPrime;
          double zAdjusted;
          double zPrime;

          yPrime=(yMax-y)*cosTilt-z*sinTilt;
          zPrime=(yMax-y)*sinTilt+z*cosTilt;
          zAdjusted=(yMax/2)+relDistOfUserFromScreen
           *(zPrime-(yMax/2))/(yPrime+relDistOfUserFromScreen);
          xAdjusted=(xMax/2)+relDistOfUserFromScreen
           *(x-(xMax/2))/(yPrime+relDistOfUserFromScreen);
          xAdjusted=xAdjusted+xOffset;
          corner=new POINT();
          corner.x=(int) (pixelsPerX*xAdjusted);
          corner.y=screen.height-(int) (pixelsPerZ*zAdjusted);
          return corner;
        }

    private void displayQuadrilateral(
      double x0,
      double y0,
      double z0,
      double x1,
      double y1,
      double z1,
      double x2,
      double y2,
      double z2,
      double x3,
      double y3,
      double z3,
      int    shade)
        {
          int   i;
          POINT quadrilateral [];

          quadrilateral=new POINT [4];
          quadrilateral[0]=getCorner(x0,y0,z0);
          quadrilateral[1]=getCorner(x1,y1,z1);
          quadrilateral[2]=getCorner(x2,y2,z2);
          quadrilateral[3]=getCorner(x3,y3,z3);
          drawQuadrilateral(quadrilateral,shade);
          return;
        }

    private void drawTriangle(
      POINT box [],
      int   colorNum)
        {
          int i;
          int x [];
          int y [];

          x=new int [3];
          y=new int [3];
          for (i=0; i < 3; ++i)
            {
              x[i]=box[i].x;
              y[i]=box[i].y;
            }
          graph.setColor(redGreenBlue[colorNum]);
          graph.fillPolygon(x,y,3);
          return;
        }

    private void displayTriangle(
      double x0,
      double y0,
      double z0,
      double x1,
      double y1,
      double z1,
      double x2,
      double y2,
      double z2,
      int    shade)
        {
          int   i;
          POINT triangle [];

          triangle=new POINT [3];
          triangle[0]=getCorner(x0,y0,z0);
          triangle[1]=getCorner(x1,y1,z1);
          triangle[2]=getCorner(x2,y2,z2);
          drawTriangle(triangle,shade);
          return;
        }

    private void outputTriangle(
      VertexRec triangle [],
      boolean   firstPass,
      int       faceColor)
        {
          double x0;
          double x1;
          double x2;
          double x3;
          double y0;
          double y1;
          double y2;
          double y3;

          if (firstPass)
            {
              if ((triangle[1].x < xMax/2.0) && (triangle[1].x > triangle[0].x))
                {
                  x0=triangle[2].x;
                  y0=triangle[2].y;
                  x1=triangle[1].x;
                  y1=triangle[1].y;
                  x2=triangle[1].x;
                  y2=triangle[1].y;
                  x3=triangle[2].x;
                  y3=triangle[2].y;
                  displayQuadrilateral(x0,y0,RELATIVE_HEIGHT_OF_WALL,x1,y1,
                   RELATIVE_HEIGHT_OF_WALL,x2,y2,0.0,x3,y3,0.0,
                   TRIANGLE_SSW_NNE_COLOR);
                }
              if ((triangle[1].x > xMax/2.0) && (triangle[1].x < triangle[2].x))
                {
                  x0=triangle[1].x;
                  y0=triangle[1].y;
                  x1=triangle[0].x;
                  y1=triangle[0].y;
                  x2=triangle[0].x;
                  y2=triangle[0].y;
                  x3=triangle[1].x;
                  y3=triangle[1].y;
                  displayQuadrilateral(x0,y0,RELATIVE_HEIGHT_OF_WALL,x1,y1,
                   RELATIVE_HEIGHT_OF_WALL,x2,y2,0.0,x3,y3,0.0,
                   TRIANGLE_SSE_NNW_COLOR);
                }
            }
          else
            {
              x0=triangle[0].x;
              y0=triangle[0].y;
              x1=triangle[2].x;
              y1=triangle[2].y;
              x2=triangle[2].x;
              y2=triangle[2].y;
              x3=triangle[0].x;
              y3=triangle[0].y;
              displayQuadrilateral(x0,y0,RELATIVE_HEIGHT_OF_WALL,
               x1,y1,RELATIVE_HEIGHT_OF_WALL,x2,y2,0.0,x3,y3,0.0,faceColor);
              x0=triangle[0].x;
              y0=triangle[0].y;
              x1=triangle[1].x;
              y1=triangle[1].y;
              x2=triangle[2].x;
              y2=triangle[2].y;
              displayTriangle(x0,y0,RELATIVE_HEIGHT_OF_WALL,
               x1,y1,RELATIVE_HEIGHT_OF_WALL,x2,y2,RELATIVE_HEIGHT_OF_WALL,
               TOP_COLOR);
            }
          return;
        }

    private void outputRectangle(
      VertexRec rectangle [],
      int       faceColor)
        {
          double x0;
          double x1;
          double x2;
          double x3;
          double y0;
          double y1;
          double y2;
          double y3;

          x0=rectangle[3].x;
          y0=rectangle[3].y;
          x1=rectangle[2].x;
          y1=rectangle[2].y;
          x2=rectangle[2].x;
          y2=rectangle[2].y;
          x3=rectangle[3].x;
          y3=rectangle[3].y;
          displayQuadrilateral(x0,y0,RELATIVE_HEIGHT_OF_WALL,x1,
           y1,RELATIVE_HEIGHT_OF_WALL,x2,y2,0.0,x3,y3,0.0,faceColor);
          x0=rectangle[0].x;
          y0=rectangle[0].y;
          x1=rectangle[1].x;
          y1=rectangle[1].y;
          x2=rectangle[2].x;
          y2=rectangle[2].y;
          x3=rectangle[3].x;
          y3=rectangle[3].y;
          displayQuadrilateral(x0,y0,RELATIVE_HEIGHT_OF_WALL,x1,
           y1,RELATIVE_HEIGHT_OF_WALL,x2,y2,RELATIVE_HEIGHT_OF_WALL,x3,y3,
           RELATIVE_HEIGHT_OF_WALL,TOP_COLOR);
          return;
        }

    private void outputLeftRight(
      VertexRec rectangle [])
        {
          double x0;
          double x1;
          double x2;
          double x3;
          double y0;
          double y1;
          double y2;
          double y3;

          if (2.0*rectangle[0].x > xMax)
            {
              x0=rectangle[0].x;
              y0=rectangle[0].y;
              x1=rectangle[3].x;
              y1=rectangle[3].y;
              x2=rectangle[3].x;
              y2=rectangle[3].y;
              x3=rectangle[0].x;
              y3=rectangle[0].y;
              displayQuadrilateral(x0,y0,RELATIVE_HEIGHT_OF_WALL,x1,y1,
               RELATIVE_HEIGHT_OF_WALL,x2,y2,0.0,x3,y3,0.0,
               RECTANGLE_SE_NW_COLOR);
            }
          if (2.0*rectangle[1].x < xMax)
            {
              x0=rectangle[2].x;
              y0=rectangle[2].y;
              x1=rectangle[1].x;
              y1=rectangle[1].y;
              x2=rectangle[1].x;
              y2=rectangle[1].y;
              x3=rectangle[2].x;
              y3=rectangle[2].y;
              displayQuadrilateral(x0,y0,RELATIVE_HEIGHT_OF_WALL,x1,y1,
               RELATIVE_HEIGHT_OF_WALL,x2,y2,0.0,x3,y3,0.0,
               RECTANGLE_SW_NE_COLOR);
            }
          return;
        }

    private void drawLine(
      double x1,
      double y1,
      double x2,
      double y2)
        {
          int   lineX1;
          int   lineX2;
          int   lineY1;
          int   lineY2;
          POINT tem;

          tem=getCorner(x1,y1,RELATIVE_HEIGHT_OF_WALL);
          lineX1=tem.x;
          lineY1=tem.y;
          tem=getCorner(x2,y2,RELATIVE_HEIGHT_OF_WALL);
          lineX2=tem.x;
          lineY2=tem.y;
          graph.drawLine(lineX1,lineY1,lineX2,lineY2);
          return;
        }

    private void hash()
      {
        int iteration;
        int seed0;
        int seed1;
        int seed2;
        int seed3;
        int seed4;
        int seed5;
        int seed6;
        int seed7;
        int substitutionIndex;
        int tem0;
        int tem1;
        int tem2;

        seed0=counter0;
        seed1=counter1;
        seed2=counter2;
        seed3=counter3;
        seed4=counter4;
        seed5=counter5;
        seed6=counter6;
        seed7=counter7;
        for (iteration=8; iteration > 0; --iteration)
          {
            substitutionIndex=10*seed1+seed0;
            tem0=SUBSTITUTION_LOW[substitutionIndex];
            tem1=SUBSTITUTION_HIGH[substitutionIndex];
            substitutionIndex=10*seed3+seed2;
            seed0=SUBSTITUTION_LOW[substitutionIndex];
            tem2=SUBSTITUTION_HIGH[substitutionIndex];
            substitutionIndex=10*seed5+seed4;
            seed2=SUBSTITUTION_LOW[substitutionIndex];
            seed1=SUBSTITUTION_HIGH[substitutionIndex];
            substitutionIndex=10*seed7+seed6;
            seed5=SUBSTITUTION_LOW[substitutionIndex];
            seed7=SUBSTITUTION_HIGH[substitutionIndex];
            seed3=tem0;
            seed6=tem1;
            seed4=tem2;
          }
        counter0=seed0;
        counter1=seed1;
        counter2=seed2;
        counter3=seed3;
        counter4=seed4;
        counter5=seed5;
        counter6=seed6;
        counter7=seed7;
        return;
      }

    private void increment()
      {
        int tem;

        tem=(counter0)+1;
        if (tem <= 9)
          counter0=tem;
        else
          {
            counter0=0;
            tem=(counter1)+1;
            if (tem <= 9)
              counter1=tem;
            else
              {
                counter1=0;
                tem=(counter2)+1;
                if (tem <= 9)
                  counter2=tem;
                else
                  {
                    counter2=0;
                    tem=(counter3)+1;
                    if (tem <= 9)
                      counter3=tem;
                    else
                      {
                        counter3=0;
                        tem=(counter4)+1;
                        if (tem <= 9)
                          counter4=tem;
                        else
                          {
                            counter4=0;
                            tem=(counter5)+1;
                            if (tem <= 9)
                              counter5=tem;
                            else
                              {
                                counter5=0;
                                tem=(counter6)+1;
                                if (tem <= 9)
                                  counter6=tem;
                                else
                                  {
                                    counter6=0;
                                    tem=(counter7)+1;
                                    if (tem <= 9)
                                      counter7=tem;
                                    else
                                      counter7=0;
                                  }
                              }
                          }
                      }
                  }
              }
          }
        return;
      }

    public void hexKey(
      int deltaIndex1)
        {
          boolean passageFound;
          int     xNext;
          double  xRelativeNext;
          int     yNext;
          double  yRelativeNext;

          yNext=0;
          xRelativeNext=0.0;
          yRelativeNext=0.0;
          passageFound=true;
          xNext=userX+hexDeltaX[deltaIndex1][0];
          if (xNext <= 0)
            passageFound=false;
          else
            if (xNext >= maxX)
              passageFound=false;
            else
              yNext=userY+hexDeltaY[deltaIndex1][0];
              if (yNext <= 0)
                passageFound=false;
              else
                if (yNext > maxY)
                  passageFound=false;
                else
                  {
                    if (userPage[yNext][xNext] == '\000')
                      passageFound=false;
                  }
          if (passageFound)
            {
              xNext=xNext+hexDeltaX[deltaIndex1][0];
              yNext=yNext+hexDeltaY[deltaIndex1][0];
              if (yNext < maxY)
                {
                  if (userPage[yNext][xNext] == '\001')
                    {
                      graph.setColor(redGreenBlue[BACKOUT_COLOR]);
                      userPage[userY][userX]='\003';
                    }
                  else
                    {
                      graph.setColor(redGreenBlue[ADVANCE_COLOR]);
                      userPage[yNext][xNext]='\001';
                    }
                  switch (yNext - userY)
                    {
                      case -4:
                        xRelativeNext=userXRelative;
                        yRelativeNext=userYRelative-sqrt3;
                        break;
                      case -2:
                        if (xNext > userX)
                          {
                            xRelativeNext=userXRelative+3.0/2.0;
                            yRelativeNext=userYRelative-sqrt3/2.0;
                          }
                        else
                          {
                            xRelativeNext=userXRelative-3.0/2.0;
                            yRelativeNext=userYRelative-sqrt3/2.0;
                          }
                        break;
                      case 2:
                        if (xNext > userX)
                          {
                            xRelativeNext=userXRelative+3.0/2.0;
                            yRelativeNext=userYRelative+sqrt3/2.0;
                          }
                        else
                          {
                            xRelativeNext=userXRelative-3.0/2.0;
                            yRelativeNext=userYRelative+sqrt3/2.0;
                          }
                        break;
                      default:
                        xRelativeNext=userXRelative;
                        yRelativeNext=userYRelative+sqrt3;
                        break;
                    }
                  drawLine(userXRelative,userYRelative,xRelativeNext,
                   yRelativeNext);
                }
              else
                {
                  graph.setColor(redGreenBlue[ADVANCE_COLOR]);
                  drawLine(userXRelative,userYRelative,userXRelative,yMax);
                  userHasSolved=true;
                }
              userX=xNext;
              userY=yNext;
              userXRelative=xRelativeNext;
              userYRelative=yRelativeNext;
            }
        }

    public void hexDisplayUserMoves()
      {
        int     deltaIndex;
        boolean evenRow;
        int     x;
        int     xNext;
        int     xNextNext;
        double  xRelative;
        double  xRelativeNext;
        int     y;
        int     yNext;
        int     yNextNext;
        double  yRelative;
        double  yRelativeNext;

        xRelative=0.0;
        y=2;
        yRelative=sqrt3/2.0;
        evenRow=false;
        while (y < maxY)
          {
            if (evenRow)
              {
                x=7;
                xRelative=2.5;
              }
            else
              {
                x=3;
                xRelative=1.0;
              }
            while (x < maxX)
              {
                if ((userPage[y][x] == '\001') || (userPage[y][x] == '\003'))
                  for (deltaIndex=0; deltaIndex < 6; ++deltaIndex)
                    {
                      xNext=x+hexDeltaX[deltaIndex][0];
                      yNext=y+hexDeltaY[deltaIndex][0];
                      if (userPage[yNext][xNext] != '\000')
                        {
                          if (yNext == 0)
                            {
                              graph.setColor(redGreenBlue[ADVANCE_COLOR]);
                              drawLine(1.0,0.0,xRelative,yRelative);
                            }
                          else
                            if (yNext == maxY)
                              {
                                if (userHasSolved)
                                  {
                                    graph.setColor(redGreenBlue[ADVANCE_COLOR]);
                                    yRelativeNext=yRelative+sqrt3/2.0;
                                    drawLine(xRelative,yRelative,xRelative,
                                     yRelativeNext);
                                  }
                              }
                            else
                              {
                                xNextNext=xNext+hexDeltaX[deltaIndex][0];
                                if (xNextNext > 0)
                                  {
                                    if (xNextNext < maxX)
                                      {
                                        yNextNext
                                         =yNext+hexDeltaY[deltaIndex][0];
                                        if (yNextNext > 0)
                                          {
                                            if (yNextNext < maxY)
                                              {
                                                if ((userPage[yNextNext][
                                                 xNextNext] == '\001')
                                                ||  (userPage[yNextNext][
                                                 xNextNext] == '\003'))
                                                  {
                                                    if (userPage[y][x]
                                                     == userPage[yNextNext][
                                                     xNextNext])
                                                      if (userPage[y][x]
                                                       == '\001')
                                                        graph.setColor(
                                                         redGreenBlue[
                                                         ADVANCE_COLOR]);
                                                      else
                                                        graph.setColor(
                                                         redGreenBlue[
                                                         BACKOUT_COLOR]);
                                                    else
                                                      graph.setColor(
                                                       redGreenBlue[
                                                       BACKOUT_COLOR]);
                                                    switch (yNext - y)
                                                      {
                                                        case -2:
                                                          xRelativeNext
                                                           =xRelative;
                                                          yRelativeNext
                                                           =yRelative
                                                           -sqrt3/2.0;
                                                          break;
                                                        case -1:
                                                          if (xNext > x)
                                                            {
                                                              xRelativeNext
                                                               =xRelative
                                                               +3.0/4.0;
                                                              yRelativeNext
                                                               =yRelative
                                                               -sqrt3/4.0;
                                                            }
                                                          else
                                                            {
                                                              xRelativeNext
                                                               =xRelative
                                                               -3.0/4.0;
                                                              yRelativeNext
                                                               =yRelative
                                                               -sqrt3/4.0;
                                                            }
                                                          break;
                                                        case 1:
                                                          if (xNext > x)
                                                            {
                                                              xRelativeNext
                                                               =xRelative
                                                               +3.0/4.0;
                                                              yRelativeNext
                                                               =yRelative
                                                               +sqrt3/4.0;
                                                            }
                                                          else
                                                            {
                                                              xRelativeNext
                                                               =xRelative
                                                               -3.0/4.0;
                                                              yRelativeNext
                                                               =yRelative
                                                               +sqrt3/4.0;
                                                            }
                                                          break;
                                                        default:
                                                          xRelativeNext
                                                           =xRelative;
                                                          yRelativeNext
                                                           =yRelative+sqrt3/2.0;
                                                          break;
                                                      }
                                                    drawLine(xRelative,
                                                     yRelative,xRelativeNext,
                                                     yRelativeNext);
                                                  }
                                               }
                                          }
                                      }
                                  }
                              }
                        }
                    }
                xRelative+=3.0;
                x+=8;
              }
            evenRow=! evenRow;
            yRelative+=sqrt3/2.0;
            y+=2;
          }
        if (userHasSolved)
          {
            graph.setColor(redGreenBlue[ADVANCE_COLOR]);
            drawLine(xRelative,yRelative,xRelative,yMax);
          }
      }

    private void hexSolveMaze()
        {
          int     deltaIndex;
          boolean passageFound;
          int     stackHead;
          int     x;
          int     xNext;
          int     y;
          int     yNext;

          numRoomsInSolution=1;
          adjacency=0;
          x=3;
          y=2;
          stackHead=-1;
          computerPage[y][x]='\001';
          yNext=0;
          xNext=0;
          do
            {
              deltaIndex=0;
              passageFound=false;
              do
                {
                  while ((deltaIndex < 6) && (! passageFound))
                    {
                      xNext=x+hexDeltaX[deltaIndex][0];
                      yNext=y+hexDeltaY[deltaIndex][0];
                      if (computerPage[yNext][xNext] == '\002')
                        passageFound=true;
                      else
                        ++deltaIndex;
                    }
                  if (! passageFound)
                    {
                      deltaIndex=stack[stackHead].index1;
                      computerPage[y][x]='\002';
                      x-=hexDeltaX[deltaIndex][0];
                      y-=hexDeltaY[deltaIndex][0];
                      computerPage[y][x]='\002';
                      x-=hexDeltaX[deltaIndex][0];
                      y-=hexDeltaY[deltaIndex][0];
                      --stackHead;
                      ++deltaIndex;
                    }
                }
              while (! passageFound);
              computerPage[yNext][xNext]='\001';
              xNext+=hexDeltaX[deltaIndex][0];
              yNext+=hexDeltaY[deltaIndex][0];
              if (yNext <= maxY)
                {
                  stack[++stackHead].index1=(short) deltaIndex;
                  computerPage[yNext][xNext]='\001';
                  x=xNext;
                  y=yNext;
                }
            }
          while (yNext < maxY);
          x=maxX-3;
          y=maxY-2;
          adjacency=0;
          while (stackHead >= 0)
            {
              for (deltaIndex=0; deltaIndex < 6; ++deltaIndex)
                {
                  xNext=x+hexDeltaX[deltaIndex][0];
                  yNext=y+hexDeltaY[deltaIndex][0];
                  if (computerPage[yNext][xNext] != '\001')
                    {
                      if (computerPage[yNext][xNext] == '\000')
                        {
                          xNext+=hexDeltaX[deltaIndex][0];
                          yNext+=hexDeltaY[deltaIndex][0];
                          if (xNext < 0)
                            ++adjacency;
                          else
                            if (xNext > maxX)
                              ++adjacency;
                            else
                              if (yNext < 0)
                                ++adjacency;
                              else
                                if (yNext > maxY)
                                  ++adjacency;
                                else
                                  {
                                    if (computerPage[yNext][xNext] == '\001')
                                      ++adjacency;
                                  }
                        }
                    }
                }
              x-=2*hexDeltaX[stack[stackHead].index1][0];
              y-=2*hexDeltaY[stack[stackHead--].index1][0];
              ++numRoomsInSolution;
            }
          for (deltaIndex=0; deltaIndex < 6; ++deltaIndex)
            {
              xNext=x+hexDeltaX[deltaIndex][0];
              yNext=x+hexDeltaY[deltaIndex][0];
              if (computerPage[yNext][xNext] != '\002')
                {
                  if (computerPage[yNext][xNext] == '\000')
                    {
                      xNext+=hexDeltaX[deltaIndex][0];
                      yNext+=hexDeltaY[deltaIndex][0];
                      if (xNext < 0)
                        ++adjacency;
                      else
                        if (xNext > maxX)
                          ++adjacency;
                        else
                          if (yNext < 0)
                            ++adjacency;
                          else
                            if (yNext > maxY)
                              ++adjacency;
                            else
                              {
                                if (computerPage[yNext][xNext] == '\001')
                                  ++adjacency;
                              }
                    }
                }
            }
          return;
        }

    private void hexGenerateMaze(
      int      seed [])
        {
          int     columnNum;
          int     deltaIndex1;
          int     deltaIndex2;
          boolean passageFound;
          int     rN [];
          int     rNIndex1;
          int     rNIndex2;
          int     rowNum;
          boolean searchComplete;
          int     stackHead;
          int     temInt;
          int     x;
          int     xMod8;
          int     xNext;
          int     y;
          int     yMod4;
          int     yNext;

          yNext=0;
          xNext=0;
          rN=new int [8];
          rN[0]=seed[0]+1;
          rN[1]=seed[1]+1;
          rN[2]=seed[2]+1;
          rN[3]=seed[3]+1;
          rN[4]=seed[4]+1;
          rN[5]=seed[5]+1;
          rN[6]=seed[6]+1;
          rN[7]=seed[7]+1;
          yMod4=1;
          for (y=0; y <= maxY; ++y)
            {
              if (yMod4 == 1)
                {
                  xMod8=1;
                  for (x=0; x <= maxX; ++x)
                    {
                      if ((((xMod8 == 0) && (y != 0) && (y != maxY))
                      ||  (xMod8 == 3) || (xMod8 == 4) || (xMod8 == 5)))
                        computerPage[y][x]='\000';
                      else
                        computerPage[y][x]='\002';
                      if (++xMod8 >= 8)
                        xMod8=0;
                    }
                }
              else
                if ((yMod4 == 0) || (yMod4 == 2))
                  {
                    xMod8=1;
                    for (x=0; x <= maxX; ++x)
                      {
                        if ((xMod8 == 2) || (xMod8 == 6))
                          computerPage[y][x]='\000';
                        else
                          computerPage[y][x]='\002';
                        if (++xMod8 >= 8)
                          xMod8=0;
                      }
                  }
                else
                  {
                    xMod8=1;
                    for (x=0; x <= maxX; ++x)
                      {
                        if ((xMod8 == 0) || (xMod8 == 1) || (xMod8 == 4)
                        ||  (xMod8 == 7))
                          computerPage[y][x]='\000';
                        else
                          computerPage[y][x]='\002';
                        if (++xMod8 >= 8)
                          xMod8=0;
                      }
                  }
              if (++yMod4 >= 4)
                yMod4=0;
            }
          columnNum=rN[0];
          rNIndex1=0;
          rNIndex2=1;
          while (rNIndex2 < 8)
            {
              temInt=rN[rNIndex2];
              rN[rNIndex1]=temInt;
              columnNum+=temInt;
              if (columnNum >= 727)
                columnNum-=727;
              rNIndex1=rNIndex2++;
            }
          rN[7]=columnNum;
          columnNum%=numColumns;
          x=4*columnNum+3;
          rowNum=rN[0];
          rNIndex1=0;
          rNIndex2=1;
          while (rNIndex2 < 8)
            {
              temInt=rN[rNIndex2];
              rN[rNIndex1]=temInt;
              rowNum+=temInt;
              if (rowNum >= 727)
                rowNum-=727;
              rNIndex1=rNIndex2++;
            }
          rN[7]=rowNum;
          if (columnNum%2 == 0)
            {
              rowNum%=numRows;
              y=4*rowNum+2;
            }
          else
            {
              rowNum%=(numRows-1);
              y=4*rowNum+4;
            }
          computerPage[y][x]='\002';
          stackHead=-1;
          do
            {
              deltaIndex1=0;
              do
                {
                  deltaIndex2=rN[0];
                  rNIndex1=0;
                  rNIndex2=1;
                  while (rNIndex2 < 8)
                    {
                      temInt=rN[rNIndex2];
                      rN[rNIndex1]=temInt;
                      deltaIndex2+=temInt;
                      if (deltaIndex2 >= 727)
                        deltaIndex2-=727;
                      rNIndex1=rNIndex2++;
                    }
                  rN[7]=deltaIndex2;
                }
              while (deltaIndex2 >= 720);
              passageFound=false;
              searchComplete=false;
              while (! searchComplete)
                {
                  while ((deltaIndex1 < 6) && (! passageFound))
                    {
                      xNext=x+2*hexDeltaX[deltaIndex1][deltaIndex2];
                      if (xNext <= 0)
                        ++deltaIndex1;
                      else
                        if (xNext > maxX)
                          ++deltaIndex1;
                        else
                          {
                            yNext=y+2*hexDeltaY[deltaIndex1][deltaIndex2];
                            if (yNext <= 0)
                              ++deltaIndex1;
                            else
                              if (yNext > maxY)
                                ++deltaIndex1;
                              else
                                if (computerPage[yNext][xNext] == '\000')
                                  passageFound=true;
                                else
                                  ++deltaIndex1;
                          }
                    }
                  if (! passageFound)
                    {
                      if (stackHead >= 0)
                        {
                          deltaIndex1=stack[stackHead].index1;
                          deltaIndex2=stack[stackHead--].index2;
                          x-=2*hexDeltaX[deltaIndex1][deltaIndex2];
                          y-=2*hexDeltaY[deltaIndex1++][deltaIndex2];
                        }
                    }
                  searchComplete
                   =(passageFound || ((stackHead == -1) && (deltaIndex1 >= 6)));
                }
              if (passageFound)
                {
                  stack[++stackHead].index1=(short) deltaIndex1;
                  stack[stackHead].index2=(short) deltaIndex2;
                  computerPage[yNext][xNext]='\002';
                  computerPage[(y+yNext)/2][(x+xNext)/2]='\002';
                  x=xNext;
                  y=yNext;
                }
            }
          while (stackHead != -1);
          computerPage[0][3]='\001';
          computerPage[maxY][maxX-3]='\002';
          return;
        }

    private void hexSelectMaze()
        {
          Date   today;
          double elapsedTime;
          int    minAdjacency;
          int    numRoomsInSolutionAtMin;
          int    seedByte [];
          int    seedByteAtMin [];
          double startTime;

          adjacency=0;
          numRoomsInSolution=0;
          seedByte=new int [8];
          seedByteAtMin=new int [8];
          counter0=seed[0];
          counter1=seed[1];
          counter2=seed[2];
          counter3=seed[3];
          counter4=seed[4];
          counter5=seed[5];
          counter6=seed[6];
          counter7=seed[7];
          hash();
          minAdjacency=4*numRoomsInMaze+1;
          numRoomsInSolutionAtMin=0;
          seedByteAtMin[0]=counter0;
          seedByteAtMin[1]=counter1;
          seedByteAtMin[2]=counter2;
          seedByteAtMin[3]=counter3;
          seedByteAtMin[4]=counter4;
          seedByteAtMin[5]=counter5;
          seedByteAtMin[6]=counter6;
          seedByteAtMin[7]=counter7;
          today=new Date();
          startTime=((double) today.getTime())/1000.0;
          do
            {
              seedByte[0]=counter0;
              seedByte[1]=counter1;
              seedByte[2]=counter2;
              seedByte[3]=counter3;
              seedByte[4]=counter4;
              seedByte[5]=counter5;
              seedByte[6]=counter6;
              seedByte[7]=counter7;
              hexGenerateMaze(seedByte);
              hexSolveMaze();
              if (3*numRoomsInSolution >= numRoomsInMaze)
                {
                  if (adjacency < minAdjacency)
                    {
                      minAdjacency=adjacency;
                      numRoomsInSolutionAtMin=numRoomsInSolution;
                      seedByteAtMin[0]=seedByte[0];
                      seedByteAtMin[1]=seedByte[1];
                      seedByteAtMin[2]=seedByte[2];
                      seedByteAtMin[3]=seedByte[3];
                      seedByteAtMin[4]=seedByte[4];
                      seedByteAtMin[5]=seedByte[5];
                      seedByteAtMin[6]=seedByte[6];
                      seedByteAtMin[7]=seedByte[7];
                    }
                  else
                    {
                      if (adjacency == minAdjacency)
                        {
                          if (numRoomsInSolution > numRoomsInSolutionAtMin)
                            {
                              numRoomsInSolutionAtMin=numRoomsInSolution;
                              seedByteAtMin[0]=seedByte[0];
                              seedByteAtMin[1]=seedByte[1];
                              seedByteAtMin[2]=seedByte[2];
                              seedByteAtMin[3]=seedByte[3];
                              seedByteAtMin[4]=seedByte[4];
                              seedByteAtMin[5]=seedByte[5];
                              seedByteAtMin[6]=seedByte[6];
                              seedByteAtMin[7]=seedByte[7];
                            }
                        }
                    }
                }
              increment();
              today=new Date();
              elapsedTime=((double) today.getTime())/1000.0-startTime;
            }
          while ((elapsedTime >= 0.0)
          &&     (elapsedTime < SECONDS_FOR_MAZE_SELECTION));
          hexGenerateMaze(seedByteAtMin);
          hexSolveMaze();
          return;
        }

    public void hexDisplaySolution()
        {
          int     deltaIndex;
          boolean pathFound;
          int     x;
          int     xNext;
          int     xPrevious;
          double  xRelative;
          double  xRelativeNext;
          int     y;
          int     yNext;
          int     yPrevious;
          double  yRelative;
          double  yRelativeNext;

          xRelative=1.0;
          yRelative=sqrt3/2.0;
          xRelativeNext=0.0;
          yRelativeNext=0.0;
          graph.setColor(redGreenBlue[SOLUTION_COLOR]);
          drawLine(1.0,0.0,xRelative,yRelative);
          xPrevious=3;
          yPrevious=-2;
          x=3;
          y=2;
          xNext=0;
          yNext=0;
          do
            {
              pathFound=false;
              deltaIndex=0;
              while (! pathFound)
                {
                  xNext=x+hexDeltaX[deltaIndex][0];
                  yNext=y+hexDeltaY[deltaIndex][0];
                  if (computerPage[yNext][xNext] == '\001')
                    {
                      xNext+=hexDeltaX[deltaIndex][0];
                      yNext+=hexDeltaY[deltaIndex][0];
                      if ((xNext != xPrevious) || (yNext != yPrevious))
                        pathFound=true;
                      else
                        ++deltaIndex;
                    }
                  else
                    ++deltaIndex;
                }
              if (yNext < maxY)
                {
                  switch (yNext-y)
                    {
                      case -4:
                        xRelativeNext=xRelative;
                        yRelativeNext=yRelative-sqrt3;
                        break;
                      case -2:
                        if (xNext > x)
                          {
                            xRelativeNext=xRelative+3.0/2.0;
                            yRelativeNext=yRelative-sqrt3/2.0;
                          }
                        else
                          {
                            xRelativeNext=xRelative-3.0/2.0;
                            yRelativeNext=yRelative-sqrt3/2.0;
                          }
                        break;
                      case 2:
                        if (xNext > x)
                          {
                            xRelativeNext=xRelative+3.0/2.0;
                            yRelativeNext=yRelative+sqrt3/2.0;
                          }
                        else
                          {
                            xRelativeNext=xRelative-3.0/2.0;
                            yRelativeNext=yRelative+sqrt3/2.0;
                          }
                        break;
                      default:
                        xRelativeNext=xRelative;
                        yRelativeNext=yRelative+sqrt3;
                        break;
                    }
                  drawLine(xRelative,yRelative,xRelativeNext,yRelativeNext);
                }
              else
                drawLine(xRelative,yRelative,xRelative,yMax);
              xPrevious=x;
              yPrevious=y;
              x=xNext;
              y=yNext;
              xRelative=xRelativeNext;
              yRelative=yRelativeNext;
            }
          while (yNext < maxY);
          return;
        }

    private void hexOutputMaze()
      {
        int       cornerX [];
        int       cornerY [];
        int       digitNum;
        long      dividend;
        int       objectNum;
        long      quotient;
        double    radians;
        double    radiansPerDegree;
        int       roomNum;
        VertexRec singleRectangle [];
        VertexRec singleTriangle [];
        double    temDouble1;
        double    temDouble2;
        double    temDouble3;
        double    temDouble4;
        Date      today;
        VertexRec triangle [] [];
        int       vertexNum;
        int       xMod8;
        double    x0;
        double    x1;
        double    x2;
        double    x3;
        double    y0;
        double    y1;
        double    y2;
        double    y3;

        singleRectangle=new VertexRec [4];
        for (vertexNum=0; vertexNum < 4; ++vertexNum)
          singleRectangle[vertexNum]=new VertexRec();
        singleTriangle=new VertexRec [3];
        for (vertexNum=0; vertexNum < 3; ++vertexNum)
          singleTriangle[vertexNum]=new VertexRec();
        triangle=new VertexRec [4] [3];
        for (objectNum=0; objectNum < 4; ++objectNum)
          for (vertexNum=0; vertexNum < 3; ++vertexNum)
            triangle[objectNum][vertexNum]=new VertexRec();
        switch (state)
          {
            case 0:
              if (sizeChanged(false))
                {
                  seed=new int [8];
                  today=new Date();
                  dividend=today.getTime();
                  for (digitNum=0; digitNum < 8; ++digitNum)
                    {
                      quotient=dividend/10;
                      seed[digitNum]=(int) (dividend-10*quotient);
                      dividend=quotient;
                    }
                  temDouble1=((double) (screen.width))*0.28;
                  temDouble2=(double) MIN_WALL_LENGTH_IN_INCHES;
                  temDouble2*=25.4;
                  temDouble3=(double) RELATIVE_WIDTH_OF_WALL;
                  numColumns=(int) (2.0*(temDouble1/temDouble2-2.0
                   -temDouble3/sqrt3)/3.0+1.0);
                  if ((numColumns%2) == 0)
                    --numColumns;
                  if (numColumns < 3)
                    numColumns=3;
                  temDouble1=((double) (screen.height))*0.28;
                  temDouble2=((double) (screen.width))*0.28;
                  temDouble3=(double) numColumns;
                  temDouble4=(double) RELATIVE_WIDTH_OF_WALL;
                  numRows=(int) (((temDouble1/temDouble2)
                   *(3.0*(temDouble3-1.0)/2.0+2.0+temDouble4/sqrt3)-temDouble4)
                   /sqrt3);
                  if (numRows < 2)
                    numRows=2;
                  maxX=8*(numColumns/2)+6;
                  maxY=4*numRows;
                  numRoomsInMaze=numRows*numColumns-(numColumns/2);
                  computerPage=new char [maxY+1] [maxX+1];
                  userPage=new char [maxY+1] [maxX+1];
                  clearUserAttempts=true;
                  stack=new StackRec [numRoomsInMaze];
                  for (roomNum=0; roomNum < numRoomsInMaze; ++roomNum)
                    stack[roomNum]=new StackRec();
                  hexSelectMaze();
                }
              if (clearUserAttempts)
                {
                  for (userX=0; userX <= maxX; ++userX)
                    for (userY=0; userY <= maxY; ++userY)
                      if (computerPage[userY][userX] == '\000')
                        userPage[userY][userX]='\000';
                      else
                        userPage[userY][userX]='\002';
                  userX=3;
                  userXRelative=1.0;
                  userY=2;
                  userYRelative=sqrt3/2.0;
                  userPage[userY][userX]='\001';
                  clearUserAttempts=false;
                }
              if (paint)
                {
                  cornerX=new int [4];
                  cornerY=new int [4];
                  cornerX[0]=0;
                  cornerY[0]=0;
                  cornerX[1]=0;
                  cornerY[1]=screen.height;
                  cornerX[2]=screen.width;
                  cornerY[2]=screen.height;
                  cornerX[3]=screen.width;
                  cornerY[3]=0;
                  graph.setColor(new Color(255,255,255));
                  graph.fillPolygon(cornerX,cornerY,4);
                  radiansPerDegree=Math.atan(1.0)/45.0;
                  radians=tilt*radiansPerDegree;
                  sinTilt=Math.sin(radians);
                  cosTilt=Math.cos(radians);
                  temDouble1=(double) numColumns;
                  xMax=3.0*(temDouble1-1.0)/2.0+2.0+RELATIVE_WIDTH_OF_WALL/sqrt3;
                  temDouble1=(double) (screen.width);
                  pixelsPerX=(temDouble1-1.0)
                   /(xMax*(xMax/(xMax-RELATIVE_HEIGHT_OF_WALL)));
                  xOffset=(xMax/2.0)
                   *(RELATIVE_HEIGHT_OF_WALL/(xMax-RELATIVE_HEIGHT_OF_WALL));
                  temDouble1=(double) numRows;
                  yMax=temDouble1*sqrt3+RELATIVE_WIDTH_OF_WALL;
                  temDouble1=(double) (screen.height);
                  pixelsPerZ=(temDouble1-1.0)/Math.sqrt(yMax*yMax
                   +RELATIVE_HEIGHT_OF_WALL*RELATIVE_HEIGHT_OF_WALL);
                  if (yMax > xMax)
                    relDistOfUserFromScreen=yMax;
                  else
                    relDistOfUserFromScreen=xMax;
                  paint=false;
                }
              if (state == 0)
                state=1;
              break;
            case 1:
              baseTriangle[0][0].x=0.0;
              baseTriangle[0][0].y=RELATIVE_WIDTH_OF_WALL+sqrt3/2.0;
              baseTriangle[0][1].x=0.0;
              baseTriangle[0][1].y=sqrt3/2.0;
              baseTriangle[0][2].x=RELATIVE_WIDTH_OF_WALL*sqrt3/2.0;
              baseTriangle[0][2].y=(RELATIVE_WIDTH_OF_WALL+sqrt3)/2.0;
              baseTriangle[1][0].x=(1.0-RELATIVE_WIDTH_OF_WALL/sqrt3)/2.0;
              baseTriangle[1][0].y=RELATIVE_WIDTH_OF_WALL/2.0;
              baseTriangle[1][1].x=0.5+RELATIVE_WIDTH_OF_WALL/sqrt3;
              baseTriangle[1][1].y=0.0;
              baseTriangle[1][2].x=baseTriangle[1][1].x;
              baseTriangle[1][2].y=RELATIVE_WIDTH_OF_WALL;
              baseTriangle[2][0].x=1.5;
              baseTriangle[2][0].y=RELATIVE_WIDTH_OF_WALL;
              baseTriangle[2][1].x=1.5;
              baseTriangle[2][1].y=0.0;
              baseTriangle[2][2].x=1.5*(1.0+RELATIVE_WIDTH_OF_WALL/sqrt3);
              baseTriangle[2][2].y=RELATIVE_WIDTH_OF_WALL/2.0;
              baseTriangle[3][0].x=2.0-RELATIVE_WIDTH_OF_WALL/(2.0*sqrt3);
              baseTriangle[3][0].y=baseTriangle[0][2].y;
              baseTriangle[3][1].x=2.0+RELATIVE_WIDTH_OF_WALL/sqrt3;
              baseTriangle[3][1].y=baseTriangle[0][1].y;
              baseTriangle[3][2].x=baseTriangle[3][1].x;
              baseTriangle[3][2].y=baseTriangle[0][0].y;
              baseRectangle[0][0].x=baseTriangle[0][2].x;
              baseRectangle[0][0].y=baseTriangle[0][2].y;
              baseRectangle[0][1].x=baseTriangle[1][1].x;
              baseRectangle[0][1].y=sqrt3;
              baseRectangle[0][2].x=baseTriangle[1][0].x;
              baseRectangle[0][2].y=sqrt3+RELATIVE_WIDTH_OF_WALL/2.0;
              baseRectangle[0][3].x=baseTriangle[0][0].x;
              baseRectangle[0][3].y=baseTriangle[0][0].y;
              baseRectangle[1][0].x=baseTriangle[0][1].x;
              baseRectangle[1][0].y=baseTriangle[0][1].y;
              baseRectangle[1][1].x=baseTriangle[1][0].x;
              baseRectangle[1][1].y=baseTriangle[1][0].y;
              baseRectangle[1][2].x=baseTriangle[1][2].x;
              baseRectangle[1][2].y=baseTriangle[1][2].y;
              baseRectangle[1][3].x=baseTriangle[0][2].x;
              baseRectangle[1][3].y=baseTriangle[0][2].y;
              baseRectangle[2][0].x=baseTriangle[1][1].x;
              baseRectangle[2][0].y=baseTriangle[1][1].y;
              baseRectangle[2][1].x=baseTriangle[2][1].x;
              baseRectangle[2][1].y=baseTriangle[2][1].y;
              baseRectangle[2][2].x=baseTriangle[2][0].x;
              baseRectangle[2][2].y=baseTriangle[2][0].y;
              baseRectangle[2][3].x=baseTriangle[1][2].x;
              baseRectangle[2][3].y=baseTriangle[1][2].y;
              baseRectangle[3][0].x=baseTriangle[2][2].x;
              baseRectangle[3][0].y=baseTriangle[2][2].y;
              baseRectangle[3][1].x=baseTriangle[3][1].x;
              baseRectangle[3][1].y=baseTriangle[3][1].y;
              baseRectangle[3][2].x=baseTriangle[3][0].x;
              baseRectangle[3][2].y=baseTriangle[3][0].y;
              baseRectangle[3][3].x=baseTriangle[2][0].x;
              baseRectangle[3][3].y=baseTriangle[2][0].y;
              baseRectangle[4][0].x=baseTriangle[3][1].x;
              baseRectangle[4][0].y=baseTriangle[3][1].y;
              baseRectangle[4][1].x=baseTriangle[3][1].x
               +(baseTriangle[2][1].x-baseTriangle[1][1].x);
              baseRectangle[4][1].y=baseTriangle[3][1].y;
              baseRectangle[4][2].x=baseRectangle[4][1].x;
              baseRectangle[4][2].y=baseTriangle[3][2].y;
              baseRectangle[4][3].x=baseTriangle[3][2].x;
              baseRectangle[4][3].y=baseTriangle[3][2].y;
              baseRectangle[5][0].x=baseRectangle[0][1].x
               +(baseTriangle[2][1].x-baseTriangle[1][1].x);
              baseRectangle[5][0].y=baseRectangle[0][1].y;
              baseRectangle[5][1].x=baseTriangle[3][0].x;
              baseRectangle[5][1].y=baseTriangle[3][0].y;
              baseRectangle[5][2].x=baseTriangle[3][2].x;
              baseRectangle[5][2].y=baseTriangle[3][2].y;
              baseRectangle[5][3].x=baseRectangle[0][2].x
               +(baseTriangle[2][2].x-baseTriangle[1][0].x);
              baseRectangle[5][3].y=baseRectangle[0][2].y;
              rectangle[0][0].x=baseTriangle[1][1].x;
              rectangle[0][0].y=baseTriangle[1][1].y;
              rectangle[0][1].x=xMax-baseTriangle[1][1].x;
              rectangle[0][1].y=baseTriangle[1][1].y;
              rectangle[0][2].x=xMax-baseTriangle[1][2].x;
              rectangle[0][2].y=baseTriangle[1][2].y;
              rectangle[0][3].x=baseTriangle[1][2].x;
              rectangle[0][3].y=baseTriangle[1][2].y;
              rectangle[1][0].x=baseTriangle[0][1].x;
              rectangle[1][0].y=baseTriangle[0][1].y;
              rectangle[1][1].x=xMax-baseTriangle[0][1].x;
              rectangle[1][1].y=baseTriangle[0][1].y;
              rectangle[1][2].x=xMax-baseTriangle[1][2].x;
              rectangle[1][2].y=baseTriangle[1][2].y;
              rectangle[1][3].x=baseTriangle[1][2].x;
              rectangle[1][3].y=baseTriangle[1][2].y;
              rectangle[2][0].x=baseTriangle[0][1].x;
              rectangle[2][0].y=baseTriangle[0][1].y;
              rectangle[2][1].x=xMax-baseTriangle[0][1].x;
              rectangle[2][1].y=baseTriangle[0][1].y;
              rectangle[2][2].x=xMax-baseTriangle[0][0].x;
              rectangle[2][2].y=baseTriangle[0][0].y;
              rectangle[2][3].x=baseTriangle[0][0].x;
              rectangle[2][3].y=baseTriangle[0][0].y;
              rectangle[3][0].x=baseTriangle[0][0].x;
              rectangle[3][0].y=baseTriangle[0][0].y;
              rectangle[3][1].x=xMax-baseTriangle[0][0].x;
              rectangle[3][1].y=baseTriangle[0][0].y;
              rectangle[3][2].x=xMax-baseRectangle[0][1].x;
              rectangle[3][2].y=baseRectangle[0][1].y;
              rectangle[3][3].x=baseRectangle[0][1].x;
              rectangle[3][3].y=baseRectangle[0][1].y;
              y=0;
              state=2;
              break;
            case 2:
              if (y <= maxY-1)
                {
                  if (y > 0)
                    {
                      x0=rectangle[0][0].x;
                      y0=rectangle[0][0].y;
                      x1=rectangle[0][1].x;
                      y1=rectangle[0][1].y;
                      x2=rectangle[0][2].x;
                      y2=rectangle[0][2].y;
                      x3=rectangle[0][3].x;
                      y3=rectangle[0][3].y;
                      displayQuadrilateral(x0,y0,0.0,x1,y1,
                       0.0,x2,y2,0.0,x3,y3,0.0,FLOOR_COLOR);
                      x0=rectangle[1][0].x;
                      y0=rectangle[1][0].y;
                      x1=rectangle[1][1].x;
                      y1=rectangle[1][1].y;
                      x2=rectangle[1][2].x;
                      y2=rectangle[1][2].y;
                      x3=rectangle[1][3].x;
                      y3=rectangle[1][3].y;
                      displayQuadrilateral(x0,y0,0.0,x1,y1,
                       0.0,x2,y2,0.0,x3,y3,0.0,FLOOR_COLOR);
                    }
                  x0=rectangle[2][0].x;
                  y0=rectangle[2][0].y;
                  x1=rectangle[2][1].x;
                  y1=rectangle[2][1].y;
                  x2=rectangle[2][2].x;
                  y2=rectangle[2][2].y;
                  x3=rectangle[2][3].x;
                  y3=rectangle[2][3].y;
                  displayQuadrilateral(x0,y0,0.0,x1,y1,0.0,x2,
                   y2,0.0,x3,y3,0.0,FLOOR_COLOR);
                  if (y < maxY-4)
                    {
                      x0=rectangle[3][0].x;
                      y0=rectangle[3][0].y;
                      x1=rectangle[3][1].x;
                      y1=rectangle[3][1].y;
                      x2=rectangle[3][2].x;
                      y2=rectangle[3][2].y;
                      x3=rectangle[3][3].x;
                      y3=rectangle[3][3].y;
                      displayQuadrilateral(x0,y0,0.0,x1,y1,
                       0.0,x2,y2,0.0,x3,y3,0.0,FLOOR_COLOR);
                    }
                  for (objectNum=0; objectNum < 4; ++objectNum)
                    for (vertexNum=0; vertexNum < 4; ++vertexNum)
                      rectangle[objectNum][vertexNum].y+=sqrt3;
                  y+=4;
                }
              else
                {
                  rectangle[0][0].x=baseTriangle[1][0].x;
                  rectangle[0][0].y=baseTriangle[1][0].y;
                  rectangle[0][1].x=baseTriangle[1][1].x;
                  rectangle[0][1].y=baseTriangle[1][1].y;
                  rectangle[0][2].x=baseTriangle[2][1].x;
                  rectangle[0][2].y=baseTriangle[2][1].y;
                  rectangle[0][3].x=baseTriangle[2][2].x;
                  rectangle[0][3].y=baseTriangle[2][2].y;
                  rectangle[1][0].x=baseTriangle[0][1].x;
                  rectangle[1][0].y=baseTriangle[0][1].y;
                  rectangle[1][1].x=baseTriangle[1][0].x;
                  rectangle[1][1].y=baseTriangle[1][0].y;
                  rectangle[1][2].x=baseTriangle[2][2].x;
                  rectangle[1][2].y=baseTriangle[2][2].y;
                  rectangle[1][3].x=baseTriangle[3][1].x;
                  rectangle[1][3].y=baseTriangle[3][1].y;
                  rectangle[2][0].x=baseTriangle[0][0].x;
                  rectangle[2][0].y=baseTriangle[0][0].y;
                  rectangle[2][1].x=baseTriangle[0][1].x;
                  rectangle[2][1].y=baseTriangle[0][1].y;
                  rectangle[2][2].x=baseTriangle[3][1].x;
                  rectangle[2][2].y=baseTriangle[3][1].y;
                  rectangle[2][3].x=baseTriangle[3][2].x;
                  rectangle[2][3].y=baseTriangle[3][2].y;
                  x=0;
                  state=3;
                }
              break;
            case 3:
              if (x <= maxX)
                {
                  for (objectNum=0; objectNum < 3; ++objectNum)
                    {
                      x0=rectangle[objectNum][0].x;
                      y0=rectangle[objectNum][0].y;
                      x1=rectangle[objectNum][1].x;
                      y1=rectangle[objectNum][1].y;
                      x2=rectangle[objectNum][2].x;
                      y2=rectangle[objectNum][2].y;
                      x3=rectangle[objectNum][3].x;
                      y3=rectangle[objectNum][3].y;
                      displayQuadrilateral(x0,y0,0.0,x1,y1,
                       0.0,x2,y2,0.0,x3,y3,0.0,FLOOR_COLOR);
                      x0=rectangle[objectNum][0].x;
                      y0=yMax-rectangle[objectNum][0].y;
                      x1=rectangle[objectNum][1].x;
                      y1=yMax-rectangle[objectNum][1].y;
                      x2=rectangle[objectNum][2].x;
                      y2=yMax-rectangle[objectNum][2].y;
                      x3=rectangle[objectNum][3].x;
                      y3=yMax-rectangle[objectNum][3].y;
                      displayQuadrilateral(x0,y0,0.0,x1,y1,
                       0.0,x2,y2,0.0,x3,y3,0.0,FLOOR_COLOR);
                      for (vertexNum=0; vertexNum < 4; ++vertexNum)
                        rectangle[objectNum][vertexNum].x+=3.0;
                    }
                  x+=8;
                }
              else
                {
                  yMod4=0;
                  yOffset=0.0;
                  y=0;
                  state=4;
                }
              break;
            case 4:
              if (y <= maxY)
                {
                  switch (yMod4)
                    {
                      case 0:
                        xMod8=0;
                        for (objectNum=1; objectNum <= 2; ++objectNum)
                          for (vertexNum=0; vertexNum < 3; ++vertexNum)
                            {
                              triangle[objectNum][vertexNum].x
                               =baseTriangle[objectNum][vertexNum].x;
                              triangle[objectNum][vertexNum].y
                               =baseTriangle[objectNum][vertexNum].y+yOffset;
                            }
                        for (vertexNum=0; vertexNum < 4; ++vertexNum)
                          {
                            rectangle[2][vertexNum].x
                             =baseRectangle[2][vertexNum].x;
                            rectangle[2][vertexNum].y
                             =baseRectangle[2][vertexNum].y+yOffset;
                          }
                        for (x=0; x <= maxX; ++x)
                          {
                            switch (xMod8)
                              {
                                case 2:
                                  singleTriangle[0].x=triangle[1][0].x;
                                  singleTriangle[0].y=triangle[1][0].y;
                                  singleTriangle[1].x=triangle[1][1].x;
                                  singleTriangle[1].y=triangle[1][1].y;
                                  singleTriangle[2].x=triangle[1][2].x;
                                  singleTriangle[2].y=triangle[1][2].y;
                                  outputTriangle(singleTriangle,true,
                                   TRIANGLE_SSW_NNE_COLOR);
                                  break;
                                case 4:
                                  singleTriangle[0].x=triangle[2][0].x;
                                  singleTriangle[0].y=triangle[2][0].y;
                                  singleTriangle[1].x=triangle[2][1].x;
                                  singleTriangle[1].y=triangle[2][1].y;
                                  singleTriangle[2].x=triangle[2][2].x;
                                  singleTriangle[2].y=triangle[2][2].y;
                                  outputTriangle(singleTriangle,true,
                                   TRIANGLE_SSE_NNW_COLOR);
                                  break;
                                default:
                                  break;
                              }
                            if (++xMod8 >= 8)
                              {
                                xMod8=0;
                                for (objectNum=1; objectNum <= 2; ++objectNum)
                                  for (vertexNum=0; vertexNum < 3; ++vertexNum)
                                    triangle[objectNum][vertexNum].x+=3.0;
                                for (vertexNum=0; vertexNum < 4; ++vertexNum)
                                  rectangle[2][vertexNum].x+=3.0;
                              }
                          }
                        xMod8=0;
                        for (objectNum=1; objectNum <= 2; ++objectNum)
                          for (vertexNum=0; vertexNum < 3; ++vertexNum)
                            {
                              triangle[objectNum][vertexNum].x
                               =baseTriangle[objectNum][vertexNum].x;
                              triangle[objectNum][vertexNum].y
                               =baseTriangle[objectNum][vertexNum].y+yOffset;
                            }
                        for (vertexNum=0; vertexNum < 4; ++vertexNum)
                          {
                            rectangle[2][vertexNum].x
                             =baseRectangle[2][vertexNum].x;
                            rectangle[2][vertexNum].y
                             =baseRectangle[2][vertexNum].y+yOffset;
                          }
                        for (x=0; x <= maxX; ++x)
                          {
                            switch (xMod8)
                              {
                                case 2:
                                  singleTriangle[0].x=triangle[1][0].x;
                                  singleTriangle[0].y=triangle[1][0].y;
                                  singleTriangle[1].x=triangle[1][1].x;
                                  singleTriangle[1].y=triangle[1][1].y;
                                  singleTriangle[2].x=triangle[1][2].x;
                                  singleTriangle[2].y=triangle[1][2].y;
                                  outputTriangle(singleTriangle,false,
                                   TRIANGLE_SE_NW_COLOR);
                                  break;
                                case 3:
                                  if (computerPage[y][x] == '\000')
                                    {
                                      singleRectangle[0].x=rectangle[2][0].x;
                                      singleRectangle[0].y=rectangle[2][0].y;
                                      singleRectangle[1].x=rectangle[2][1].x;
                                      singleRectangle[1].y=rectangle[2][1].y;
                                      singleRectangle[2].x=rectangle[2][2].x;
                                      singleRectangle[2].y=rectangle[2][2].y;
                                      singleRectangle[3].x=rectangle[2][3].x;
                                      singleRectangle[3].y=rectangle[2][3].y;
                                      outputRectangle(singleRectangle,
                                       RECTANGLE_W_E_COLOR);
                                    }
                                  break;
                                case 4:
                                  singleTriangle[0].x=triangle[2][0].x;
                                  singleTriangle[0].y=triangle[2][0].y;
                                  singleTriangle[1].x=triangle[2][1].x;
                                  singleTriangle[1].y=triangle[2][1].y;
                                  singleTriangle[2].x=triangle[2][2].x;
                                  singleTriangle[2].y=triangle[2][2].y;
                                  outputTriangle(singleTriangle,false,
                                   TRIANGLE_SW_NE_COLOR);
                                  break;
                                default:
                                  break;
                              }
                            if (++xMod8 >= 8)
                              {
                                xMod8=0;
                                for (objectNum=1; objectNum <= 2; ++objectNum)
                                  for (vertexNum=0; vertexNum < 3; ++vertexNum)
                                    triangle[objectNum][vertexNum].x+=3.0;
                                for (vertexNum=0; vertexNum < 4; ++vertexNum)
                                  rectangle[2][vertexNum].x+=3.0;
                              }
                          }
                        break;
                      case 1:
                        xMod8=0;
                        for (objectNum=1; objectNum < 4; objectNum+=2)
                          for (vertexNum=0; vertexNum < 4; ++vertexNum)
                            {
                              rectangle[objectNum][vertexNum].x
                               =baseRectangle[objectNum][vertexNum].x;
                              rectangle[objectNum][vertexNum].y
                               =baseRectangle[objectNum][vertexNum].y+yOffset;
                            }
                        for (x=0; x <= maxX; ++x)
                          {
                            switch (xMod8)
                              {
                                case 1:
                                  if (computerPage[y][x] == '\000')
                                    {
                                      singleRectangle[0].x=rectangle[1][0].x;
                                      singleRectangle[0].y=rectangle[1][0].y;
                                      singleRectangle[1].x=rectangle[1][1].x;
                                      singleRectangle[1].y=rectangle[1][1].y;
                                      singleRectangle[2].x=rectangle[1][2].x;
                                      singleRectangle[2].y=rectangle[1][2].y;
                                      singleRectangle[3].x=rectangle[1][3].x;
                                      singleRectangle[3].y=rectangle[1][3].y;
                                      outputRectangle(singleRectangle,
                                       RECTANGLE_SW_NE_COLOR);
                                    }
                                  break;
                                case 5:
                                  if (computerPage[y][x] == '\000')
                                    {
                                      singleRectangle[0].x=rectangle[3][0].x;
                                      singleRectangle[0].y=rectangle[3][0].y;
                                      singleRectangle[1].x=rectangle[3][1].x;
                                      singleRectangle[1].y=rectangle[3][1].y;
                                      singleRectangle[2].x=rectangle[3][2].x;
                                      singleRectangle[2].y=rectangle[3][2].y;
                                      singleRectangle[3].x=rectangle[3][3].x;
                                      singleRectangle[3].y=rectangle[3][3].y;
                                      outputRectangle(singleRectangle,
                                       RECTANGLE_SE_NW_COLOR);
                                    }
                                  break;
                                default:
                                  break;
                              }
                            if (++xMod8 >= 8)
                              {
                                xMod8=0;
                                for (objectNum=1; objectNum < 4; objectNum+=2)
                                  for (vertexNum=0; vertexNum < 4; ++vertexNum)
                                    rectangle[objectNum][vertexNum].x+=3.0;
                              }
                          }
                        break;
                      case 2:
                        xMod8=0;
                        for (objectNum=0; objectNum < 4; objectNum+=3)
                          for (vertexNum=0; vertexNum < 3; ++vertexNum)
                            {
                              triangle[objectNum][vertexNum].x
                               =baseTriangle[objectNum][vertexNum].x;
                              triangle[objectNum][vertexNum].y
                               =baseTriangle[objectNum][vertexNum].y+yOffset;
                            }
                        for (vertexNum=0; vertexNum < 4; ++vertexNum)
                          {
                            rectangle[4][vertexNum].x
                             =baseRectangle[4][vertexNum].x;
                            rectangle[4][vertexNum].y
                             =baseRectangle[4][vertexNum].y+yOffset;
                          }
                        for (x=0; x <= maxX; ++x)
                          {
                            switch (xMod8)
                              {
                                case 0:
                                  singleTriangle[0].x=triangle[0][0].x;
                                  singleTriangle[0].y=triangle[0][0].y;
                                  singleTriangle[1].x=triangle[0][1].x;
                                  singleTriangle[1].y=triangle[0][1].y;
                                  singleTriangle[2].x=triangle[0][2].x;
                                  singleTriangle[2].y=triangle[0][2].y;
                                  outputTriangle(singleTriangle,true,
                                   TRIANGLE_SSW_NNE_COLOR);
                                  break;
                                case 6:
                                  singleTriangle[0].x=triangle[3][0].x;
                                  singleTriangle[0].y=triangle[3][0].y;
                                  singleTriangle[1].x=triangle[3][1].x;
                                  singleTriangle[1].y=triangle[3][1].y;
                                  singleTriangle[2].x=triangle[3][2].x;
                                  singleTriangle[2].y=triangle[3][2].y;
                                  outputTriangle(singleTriangle,true,
                                   TRIANGLE_SSE_NNW_COLOR);
                                  break;
                                default:
                                  break;
                              }
                            if (++xMod8 >= 8)
                              {
                                xMod8=0;
                                for (objectNum=0; objectNum < 4; objectNum+=3)
                                  for (vertexNum=0; vertexNum < 3; ++vertexNum)
                                    triangle[objectNum][vertexNum].x+=3.0;
                                for (vertexNum=0; vertexNum < 4; ++vertexNum)
                                  rectangle[4][vertexNum].x+=3.0;
                              }
                          }
                        xMod8=0;
                        for (objectNum=0; objectNum < 4; objectNum+=3)
                          for (vertexNum=0; vertexNum < 3; ++vertexNum)
                            {
                              triangle[objectNum][vertexNum].x
                               =baseTriangle[objectNum][vertexNum].x;
                              triangle[objectNum][vertexNum].y
                               =baseTriangle[objectNum][vertexNum].y+yOffset;
                            }
                        for (vertexNum=0; vertexNum < 4; ++vertexNum)
                          {
                            rectangle[4][vertexNum].x
                             =baseRectangle[4][vertexNum].x;
                            rectangle[4][vertexNum].y
                             =baseRectangle[4][vertexNum].y+yOffset;
                          }
                        for (x=0; x <= maxX; ++x)
                          {
                            switch (xMod8)
                              {
                                case 0:
                                  singleTriangle[0].x=triangle[0][0].x;
                                  singleTriangle[0].y=triangle[0][0].y;
                                  singleTriangle[1].x=triangle[0][1].x;
                                  singleTriangle[1].y=triangle[0][1].y;
                                  singleTriangle[2].x=triangle[0][2].x;
                                  singleTriangle[2].y=triangle[0][2].y;
                                  outputTriangle(singleTriangle,false,
                                   TRIANGLE_SW_NE_COLOR);
                                  break;
                                case 6:
                                  singleTriangle[0].x=triangle[3][0].x;
                                  singleTriangle[0].y=triangle[3][0].y;
                                  singleTriangle[1].x=triangle[3][1].x;
                                  singleTriangle[1].y=triangle[3][1].y;
                                  singleTriangle[2].x=triangle[3][2].x;
                                  singleTriangle[2].y=triangle[3][2].y;
                                  outputTriangle(singleTriangle,false,
                                   TRIANGLE_SE_NW_COLOR);
                                  break;
                                case 7:
                                  if (computerPage[y][x] == '\000')
                                    {
                                      singleRectangle[0].x=rectangle[4][0].x;
                                      singleRectangle[0].y=rectangle[4][0].y;
                                      singleRectangle[1].x=rectangle[4][1].x;
                                      singleRectangle[1].y=rectangle[4][1].y;
                                      singleRectangle[2].x=rectangle[4][2].x;
                                      singleRectangle[2].y=rectangle[4][2].y;
                                      singleRectangle[3].x=rectangle[4][3].x;
                                      singleRectangle[3].y=rectangle[4][3].y;
                                      outputRectangle(singleRectangle,
                                       RECTANGLE_W_E_COLOR);
                                    }
                                  break;
                                default:
                                  break;
                              }
                            if (++xMod8 >= 8)
                              {
                                xMod8=0;
                                for (objectNum=0; objectNum < 4; objectNum+=3)
                                  for (vertexNum=0; vertexNum < 3; ++vertexNum)
                                    triangle[objectNum][vertexNum].x+=3.0;
                                for (vertexNum=0; vertexNum < 4; ++vertexNum)
                                  rectangle[4][vertexNum].x+=3.0;
                              }
                          }
                        break;
                      default:
                        xMod8=0;
                        for (objectNum=0; objectNum < 6; objectNum+=5)
                          for (vertexNum=0; vertexNum < 4; ++vertexNum)
                            {
                              rectangle[objectNum][vertexNum].x
                               =baseRectangle[objectNum][vertexNum].x;
                              rectangle[objectNum][vertexNum].y
                               =baseRectangle[objectNum][vertexNum].y+yOffset;
                            }
                        for (x=0; x <= maxX; ++x)
                          {
                            switch (xMod8)
                              {
                                case 1:
                                  if (computerPage[y][x] == '\000')
                                    {
                                      singleRectangle[0].x=rectangle[0][0].x;
                                      singleRectangle[0].y=rectangle[0][0].y;
                                      singleRectangle[1].x=rectangle[0][1].x;
                                      singleRectangle[1].y=rectangle[0][1].y;
                                      singleRectangle[2].x=rectangle[0][2].x;
                                      singleRectangle[2].y=rectangle[0][2].y;
                                      singleRectangle[3].x=rectangle[0][3].x;
                                      singleRectangle[3].y=rectangle[0][3].y;
                                      outputRectangle(singleRectangle,
                                       RECTANGLE_SE_NW_COLOR);
                                    }
                                  break;
                                case 5:
                                  if (computerPage[y][x] == '\000')
                                    {
                                      singleRectangle[0].x=rectangle[5][0].x;
                                      singleRectangle[0].y=rectangle[5][0].y;
                                      singleRectangle[1].x=rectangle[5][1].x;
                                      singleRectangle[1].y=rectangle[5][1].y;
                                      singleRectangle[2].x=rectangle[5][2].x;
                                      singleRectangle[2].y=rectangle[5][2].y;
                                      singleRectangle[3].x=rectangle[5][3].x;
                                      singleRectangle[3].y=rectangle[5][3].y;
                                      outputRectangle(singleRectangle,
                                       RECTANGLE_SW_NE_COLOR);
                                    }
                                  break;
                                default:
                                  break;
                              }
                            if (++xMod8 >= 8)
                              {
                                xMod8=0;
                                for (objectNum=0; objectNum < 6; objectNum+=5)
                                  for (vertexNum=0; vertexNum < 4; ++vertexNum)
                                    rectangle[objectNum][vertexNum].x+=3.0;
                              }
                          }
                        break;
                    }
                  if (++yMod4 >= 4)
                    {
                      yMod4=0;
                      yOffset+=sqrt3;
                    }
                  ++y;
                }
              else
                state=5;
              if (state == 5)
                {
                  alreadyPainting=false;
                  hexDisplayUserMoves();
                  if (solutionDisplayed)
                    hexDisplaySolution();
                }
              break;
            default:
              break;
          }
        return;
      }

    public void sqrKey(
      int deltaIndex1)
        {
          boolean passageFound;
          double  temDouble;
          int     xNext;
          double  xRelativeNext;
          int     yNext;
          double  yRelativeNext;

          yNext=0;
          xRelativeNext=0.0;
          yRelativeNext=0.0;
          passageFound=true;
          xNext=userX+sqrDeltaX[deltaIndex1][0];
          if (xNext <= 0)
            passageFound=false;
          else
            if (xNext >= maxX)
              passageFound=false;
            else
              yNext=userY+sqrDeltaY[deltaIndex1][0];
              if (yNext <= 0)
                passageFound=false;
              else
                if (yNext > maxY)
                  passageFound=false;
                else
                  {
                    if (userPage[yNext][xNext] == '\000')
                      passageFound=false;
                  }
          if (passageFound)
            {
              xNext=xNext+sqrDeltaX[deltaIndex1][0];
              yNext=yNext+sqrDeltaY[deltaIndex1][0];
              if (yNext < maxY)
                {
                  if (userPage[yNext][xNext] == '\001')
                    {
                      graph.setColor(redGreenBlue[BACKOUT_COLOR]);
                      userPage[userY][userX]='\003';
                    }
                  else
                    {
                      graph.setColor(redGreenBlue[ADVANCE_COLOR]);
                      userPage[yNext][xNext]='\001';
                    }
                  temDouble=(double) sqrDeltaX[deltaIndex1][0];
                  xRelativeNext=userXRelative+temDouble;
                  temDouble=(double) sqrDeltaY[deltaIndex1][0];
                  yRelativeNext=userYRelative+temDouble;
                  drawLine(userXRelative,userYRelative,xRelativeNext,
                   yRelativeNext);
                }
              else
                {
                  graph.setColor(redGreenBlue[ADVANCE_COLOR]);
                  drawLine(userXRelative,userYRelative,userXRelative,yMax);
                  userHasSolved=true;
                }
              userX=xNext;
              userY=yNext;
              userXRelative=xRelativeNext;
              userYRelative=yRelativeNext;
            }
        }

    public void sqrDisplayUserMoves()
      {
        int    deltaIndex;
        double temDouble;
        int    x;
        int    xNext;
        int    xNextNext;
        double xRelative;
        double xRelativeNext;
        int    y;
        int    yNext;
        int    yNextNext;
        double yRelative;
        double yRelativeNext;

        xRelative=0.0;
        y=1;
        yRelative=(RELATIVE_WIDTH_OF_WALL+1.0)/2.0;
        while (y < maxY)
          {
            x=1;
            xRelative=(RELATIVE_WIDTH_OF_WALL+1.0)/2.0;
            while (x < maxX)
              {
                if ((userPage[y][x] == '\001')
                ||  (userPage[y][x] == '\003'))
                  for (deltaIndex=0; deltaIndex < 4; ++deltaIndex)
                    {
                      xNext=x+sqrDeltaX[deltaIndex][0];
                      yNext=y+sqrDeltaY[deltaIndex][0];
                      if (userPage[yNext][xNext] != '\000')
                        {
                          if (yNext == 0)
                            {
                              graph.setColor(redGreenBlue[ADVANCE_COLOR]);
                              drawLine(xRelative,RELATIVE_WIDTH_OF_WALL/2.0,
                               xRelative,yRelative);
                            }
                          else
                            if (yNext == maxY)
                              {
                                if (userHasSolved)
                                  {
                                    graph.setColor(redGreenBlue[ADVANCE_COLOR]);
                                    drawLine(xRelative,yRelative,xRelative,
                                     yMax);
                                  }
                              }
                            else
                              {
                                xNextNext=xNext+sqrDeltaX[deltaIndex][0];
                                if (xNextNext > 0)
                                  {
                                    if (xNextNext < maxX)
                                      {
                                        yNextNext
                                         =yNext+sqrDeltaY[deltaIndex][0];
                                        if (yNextNext > 0)
                                          {
                                            if (yNextNext < maxY)
                                              {
                                                if ((userPage[yNextNext][
                                                 xNextNext] == '\001')
                                                ||  (userPage[yNextNext][
                                                 xNextNext] == '\003'))
                                                  {
                                                    if (userPage[y][x]
                                                     == userPage[yNextNext][
                                                     xNextNext])
                                                      if (userPage[y][x]
                                                       == '\001')
                                                        graph.setColor(
                                                         redGreenBlue[
                                                         ADVANCE_COLOR]);
                                                      else
                                                        graph.setColor(
                                                         redGreenBlue[
                                                         BACKOUT_COLOR]);
                                                    else
                                                      graph.setColor(
                                                       redGreenBlue[
                                                       BACKOUT_COLOR]);
                                                    temDouble=(double)
                                                     sqrDeltaX[deltaIndex][0];
                                                    xRelativeNext=xRelative
                                                     +temDouble/2.0;
                                                    temDouble=(double)
                                                     sqrDeltaY[deltaIndex][0];
                                                    yRelativeNext=yRelative
                                                     +temDouble/2.0;
                                                    drawLine(xRelative,
                                                     yRelative,xRelativeNext,
                                                     yRelativeNext);
                                                  }
                                             }
                                          }
                                      }
                                  }
                              }
                        }
                    }
                ++xRelative;
                x+=2;
              }
            ++yRelative;
            y+=2;
          }
        if (userHasSolved)
          {
            graph.setColor(redGreenBlue[ADVANCE_COLOR]);
            drawLine(xRelative,yRelative,xRelative,yMax);
          }
      }

    private void sqrSolveMaze()
        {
          int     deltaIndex;
          boolean passageFound;
          int     stackHead;
          int     x;
          int     xNext;
          int     y;
          int     yNext;

          numRoomsInSolution=1;
          adjacency=0;
          x=1;
          y=1;
          stackHead=-1;
          computerPage[y][x]='\001';
          yNext=0;
          xNext=0;
          do
            {
              deltaIndex=0;
              passageFound=false;
              do
                {
                  while ((deltaIndex < 4) && (! passageFound))
                    {
                      xNext=x+sqrDeltaX[deltaIndex][0];
                      yNext=y+sqrDeltaY[deltaIndex][0];
                      if (computerPage[yNext][xNext] == '\002')
                        passageFound=true;
                      else
                        ++deltaIndex;
                    }
                  if (! passageFound)
                    {
                      deltaIndex=stack[stackHead].index1;
                      computerPage[y][x]='\002';
                      x-=sqrDeltaX[deltaIndex][0];
                      y-=sqrDeltaY[deltaIndex][0];
                      computerPage[y][x]='\002';
                      x-=sqrDeltaX[deltaIndex][0];
                      y-=sqrDeltaY[deltaIndex][0];
                      --stackHead;
                      ++deltaIndex;
                    }
                }
              while (! passageFound);
              computerPage[yNext][xNext]='\001';
              xNext+=sqrDeltaX[deltaIndex][0];
              yNext+=sqrDeltaY[deltaIndex][0];
              if (yNext <= maxY)
                {
                  stack[++stackHead].index1=(short) deltaIndex;
                  computerPage[yNext][xNext]='\001';
                  x=xNext;
                  y=yNext;
                }
            }
          while (yNext < maxY);
          x=maxX-1;
          y=maxY-1;
          adjacency=0;
          while (stackHead >= 0)
            {
              for (deltaIndex=0; deltaIndex < 4; ++deltaIndex)
                {
                  xNext=x+sqrDeltaX[deltaIndex][0];
                  yNext=y+sqrDeltaY[deltaIndex][0];
                  if (computerPage[yNext][xNext] != '\001')
                    {
                      if (computerPage[yNext][xNext] == '\000')
                        {
                          xNext+=sqrDeltaX[deltaIndex][0];
                          yNext+=sqrDeltaY[deltaIndex][0];
                          if (xNext < 0)
                            ++adjacency;
                          else
                            if (xNext > maxX)
                              ++adjacency;
                            else
                              if (yNext < 0)
                                ++adjacency;
                              else
                                if (yNext > maxY)
                                  ++adjacency;
                                else
                                  {
                                    if (computerPage[yNext][xNext] == '\001')
                                      ++adjacency;
                                  }
                        }
                    }
                }
              x-=2*sqrDeltaX[stack[stackHead].index1][0];
              y-=2*sqrDeltaY[stack[stackHead--].index1][0];
              ++numRoomsInSolution;
            }
          for (deltaIndex=0; deltaIndex < 4; ++deltaIndex)
            {
              xNext=x+sqrDeltaX[deltaIndex][0];
              yNext=x+sqrDeltaY[deltaIndex][0];
              if (computerPage[yNext][xNext] != '\002')
                {
                  if (computerPage[yNext][xNext] == '\000')
                    {
                      xNext+=sqrDeltaX[deltaIndex][0];
                      yNext+=sqrDeltaY[deltaIndex][0];
                      if (xNext < 0)
                        ++adjacency;
                      else
                        if (xNext > maxX)
                          ++adjacency;
                        else
                          if (yNext < 0)
                            ++adjacency;
                          else
                            if (yNext > maxY)
                              ++adjacency;
                            else
                              {
                                if (computerPage[yNext][xNext] == '\001')
                                  ++adjacency;
                              }
                    }
                }
            }
          return;
        }

    private void sqrGenerateMaze(
      int      seed [])
        {
          int     deltaIndex1;
          int     deltaIndex2;
          int     digit;
          int     digitNum;
          boolean passageFound;
          int     rN [];
          int     rNIndex1;
          int     rNIndex2;
          boolean searchComplete;
          int     stackHead;
          int     sum;
          int     temInt;
          int     x;
          int     xNext;
          int     y;
          int     yNext;

          yNext=0;
          xNext=0;
          rN=new int [8];
          rN[0]=seed[0]+1;
          rN[1]=seed[1]+1;
          rN[2]=seed[2]+1;
          rN[3]=seed[3]+1;
          rN[4]=seed[4]+1;
          rN[5]=seed[5]+1;
          rN[6]=seed[6]+1;
          rN[7]=seed[7]+1;
          for (y=0; y <= maxY; ++y)
            for (x=0; x <= maxX; ++x)
              computerPage[y][x]='\000';
          sum=0;
          for (digitNum=1; digitNum <= 3; ++digitNum)
            {
              digit=rN[0];
              rNIndex1=0;
              rNIndex2=1;
              while (rNIndex2 < 8)
                {
                  temInt=rN[rNIndex2];
                  rN[rNIndex1]=temInt;
                  digit+=temInt;
                  if (digit >= 29)
                    digit-=29;
                  rNIndex1=rNIndex2++;
                }
              rN[7]=digit;
              sum=29*sum+digit;
            }
          x=2*(sum%numColumns)+1;
          sum=0;
          for (digitNum=1; digitNum <= 3; ++digitNum)
            {
              digit=rN[0];
              rNIndex1=0;
              rNIndex2=1;
              while (rNIndex2 < 8)
                {
                  temInt=rN[rNIndex2];
                  rN[rNIndex1]=temInt;
                  digit+=temInt;
                  if (digit >= 29)
                    digit-=29;
                  rNIndex1=rNIndex2++;
                }
              rN[7]=digit;
              sum=29*sum+digit;
            }
          y=2*(sum%numRows)+1;
          computerPage[y][x]='\002';
          stackHead=-1;
          do
            {
              deltaIndex1=0;
              do
                {
                  deltaIndex2=rN[0];
                  rNIndex1=0;
                  rNIndex2=1;
                  while (rNIndex2 < 8)
                    {
                      temInt=rN[rNIndex2];
                      rN[rNIndex1]=temInt;
                      deltaIndex2+=temInt;
                      if (deltaIndex2 >= 29)
                        deltaIndex2-=29;
                      rNIndex1=rNIndex2++;
                    }
                  rN[7]=deltaIndex2;
                }
              while (deltaIndex2 >= 24);
              passageFound=false;
              searchComplete=false;
              while (! searchComplete)
                {
                  while ((deltaIndex1 < 4) && (! passageFound))
                    {
                      xNext=x+2*sqrDeltaX[deltaIndex1][deltaIndex2];
                      if (xNext <= 0)
                        ++deltaIndex1;
                      else
                        if (xNext > maxX)
                          ++deltaIndex1;
                        else
                          {
                            yNext=y+2*sqrDeltaY[deltaIndex1][deltaIndex2];
                            if (yNext <= 0)
                              ++deltaIndex1;
                            else
                              if (yNext > maxY)
                                ++deltaIndex1;
                              else
                                if (computerPage[yNext][xNext] == '\000')
                                  passageFound=true;
                                else
                                  ++deltaIndex1;
                          }
                    }
                  if (! passageFound)
                    {
                      if (stackHead >= 0)
                        {
                          deltaIndex1=stack[stackHead].index1;
                          deltaIndex2=stack[stackHead--].index2;
                          x-=2*sqrDeltaX[deltaIndex1][deltaIndex2];
                          y-=2*sqrDeltaY[deltaIndex1++][deltaIndex2];
                        }
                    }
                  searchComplete
                   =(passageFound || ((stackHead == -1) && (deltaIndex1 >= 4)));
                }
              if (passageFound)
                {
                  stack[++stackHead].index1=(short) deltaIndex1;
                  stack[stackHead].index2=(short) deltaIndex2;
                  computerPage[yNext][xNext]='\002';
                  computerPage[(y+yNext)/2][(x+xNext)/2]='\002';
                  x=xNext;
                  y=yNext;
                }
            }
          while (stackHead != -1);
          computerPage[0][1]='\001';
          computerPage[maxY][maxX-1]='\002';
          return;
        }

    private void sqrSelectMaze()
        {
          Date   today;
          double elapsedTime;
          int    minAdjacency;
          int    numRoomsInSolutionAtMin;
          int    seedByte [];
          int    seedByteAtMin [];
          double startTime;

          adjacency=0;
          numRoomsInSolution=0;
          seedByte=new int [8];
          seedByteAtMin=new int [8];
          counter0=seed[0];
          counter1=seed[1];
          counter2=seed[2];
          counter3=seed[3];
          counter4=seed[4];
          counter5=seed[5];
          counter6=seed[6];
          counter7=seed[7];
          hash();
          minAdjacency=2*numRoomsInMaze+1;
          numRoomsInSolutionAtMin=0;
          seedByteAtMin[0]=counter0;
          seedByteAtMin[1]=counter1;
          seedByteAtMin[2]=counter2;
          seedByteAtMin[3]=counter3;
          seedByteAtMin[4]=counter4;
          seedByteAtMin[5]=counter5;
          seedByteAtMin[6]=counter6;
          seedByteAtMin[7]=counter7;
          today=new Date();
          startTime=((double) today.getTime())/1000.0;
          do
            {
              seedByte[0]=counter0;
              seedByte[1]=counter1;
              seedByte[2]=counter2;
              seedByte[3]=counter3;
              seedByte[4]=counter4;
              seedByte[5]=counter5;
              seedByte[6]=counter6;
              seedByte[7]=counter7;
              sqrGenerateMaze(seedByte);
              sqrSolveMaze();
              if (3*numRoomsInSolution >= numRoomsInMaze)
                {
                  if (adjacency < minAdjacency)
                    {
                      minAdjacency=adjacency;
                      numRoomsInSolutionAtMin=numRoomsInSolution;
                      seedByteAtMin[0]=seedByte[0];
                      seedByteAtMin[1]=seedByte[1];
                      seedByteAtMin[2]=seedByte[2];
                      seedByteAtMin[3]=seedByte[3];
                      seedByteAtMin[4]=seedByte[4];
                      seedByteAtMin[5]=seedByte[5];
                      seedByteAtMin[6]=seedByte[6];
                      seedByteAtMin[7]=seedByte[7];
                    }
                  else
                    {
                      if (adjacency == minAdjacency)
                        {
                          if (numRoomsInSolution > numRoomsInSolutionAtMin)
                            {
                              numRoomsInSolutionAtMin=numRoomsInSolution;
                              seedByteAtMin[0]=seedByte[0];
                              seedByteAtMin[1]=seedByte[1];
                              seedByteAtMin[2]=seedByte[2];
                              seedByteAtMin[3]=seedByte[3];
                              seedByteAtMin[4]=seedByte[4];
                              seedByteAtMin[5]=seedByte[5];
                              seedByteAtMin[6]=seedByte[6];
                              seedByteAtMin[7]=seedByte[7];
                            }
                        }
                    }
                }
              increment();
              today=new Date();
              elapsedTime=((double) today.getTime())/1000.0-startTime;
            }
          while ((elapsedTime >= 0.0)
          &&     (elapsedTime < SECONDS_FOR_MAZE_SELECTION));
          sqrGenerateMaze(seedByteAtMin);
          sqrSolveMaze();
          return;
        }

     public void sqrDisplaySolution()
        {
          int     deltaIndex;
          boolean pathFound;
          double  temDouble;
          int     x;
          int     xNext;
          int     xPrevious;
          double  xRelative;
          double  xRelativeNext;
          int     y;
          int     yNext;
          int     yPrevious;
          double  yRelative;
          double  yRelativeNext;

          xRelative=(RELATIVE_WIDTH_OF_WALL+1.0)/2.0;
          yRelative=(RELATIVE_WIDTH_OF_WALL+1.0)/2.0;
          xRelativeNext=0.0;
          yRelativeNext=0.0;
          graph.setColor(redGreenBlue[SOLUTION_COLOR]);
          drawLine(xRelative,RELATIVE_WIDTH_OF_WALL/2.0,xRelative,yRelative);
          xPrevious=1;
          yPrevious=-1;
          x=1;
          y=1;
          xNext=0;
          yNext=0;
          do
            {
              pathFound=false;
              deltaIndex=0;
              while (! pathFound)
                {
                  xNext=x+sqrDeltaX[deltaIndex][0];
                  yNext=y+sqrDeltaY[deltaIndex][0];
                  if (computerPage[yNext][xNext] == '\001')
                    {
                      xNext+=sqrDeltaX[deltaIndex][0];
                      yNext+=sqrDeltaY[deltaIndex][0];
                      if ((xNext != xPrevious) || (yNext != yPrevious))
                        pathFound=true;
                      else
                        ++deltaIndex;
                    }
                  else
                    ++deltaIndex;
                }
              if (yNext < maxY)
                {
                  temDouble=(double) (sqrDeltaX[deltaIndex][0]);
                  xRelativeNext=xRelative+temDouble;
                  temDouble=sqrDeltaY[deltaIndex][0];
                  yRelativeNext=yRelative+temDouble;
                  drawLine(xRelative,yRelative,xRelativeNext,yRelativeNext);
                }
              else
                drawLine(xRelative,yRelative,xRelative,yMax);
              xPrevious=x;
              yPrevious=y;
              x=xNext;
              y=yNext;
              xRelative=xRelativeNext;
              yRelative=yRelativeNext;
            }
          while (yNext < maxY);
          return;
        }


    private void sqrOutputMaze()
      {
        int       cornerX [];
        int       cornerY [];
        int       digitNum;
        long      dividend;
        int       objectNum;
        long      quotient;
        double    radians;
        double    radiansPerDegree;
        int       roomNum;
        VertexRec singleRectangle [];
        double    temDouble1;
        double    temDouble2;
        double    temDouble3;
        Date      today;
        int       vertexNum;
        double    x0;
        double    x1;
        double    x2;
        double    x3;
        double    y0;
        double    y1;
        double    y2;
        double    y3;

        singleRectangle=new VertexRec [4];
        for (vertexNum=0; vertexNum < 4; ++vertexNum)
          singleRectangle[vertexNum]=new VertexRec();
        switch (state)
          {
            case 0:
              if (sizeChanged(false))
                {
                  seed=new int [8];
                  today=new Date();
                  dividend=today.getTime();
                  for (digitNum=0; digitNum < 8; ++digitNum)
                    {
                      quotient=dividend/10;
                      seed[digitNum]=(int) (dividend-10*quotient);
                      dividend=quotient;
                    }
                  temDouble1=((double) (screen.width))*0.28;
                  temDouble2=(double) MIN_WALL_LENGTH_IN_INCHES;
                  temDouble2*=25.4;
                  temDouble3=(double) RELATIVE_WIDTH_OF_WALL;
                  numColumns=(int) (temDouble1/temDouble2-temDouble3);
                  if (numColumns < 2)
                    numColumns=2;
                  temDouble1=((double) (screen.height))*0.28;
                  temDouble2=((double) (screen.width))*0.28;
                  temDouble3=(double) numColumns;
                  numRows=(int) ((temDouble1*temDouble3)/temDouble2);
                  if (numRows < 2)
                    numRows=2;
                  maxX=2*numColumns;
                  maxY=2*numRows;
                  numRoomsInMaze=numRows*numColumns;
                  computerPage=new char [maxY+1] [maxX+1];
                  userPage=new char [maxY+1] [maxX+1];
                  clearUserAttempts=true;
                  stack=new StackRec [numRoomsInMaze];
                  for (roomNum=0; roomNum < numRoomsInMaze; ++roomNum)
                    stack[roomNum]=new StackRec();
                  sqrSelectMaze();
                }
              if (clearUserAttempts)
                {
                  for (userX=0; userX <= maxX; ++userX)
                    for (userY=0; userY <= maxY; ++userY)
                      if (computerPage[userY][userX] == '\000')
                        userPage[userY][userX]='\000';
                      else
                        userPage[userY][userX]='\002';
                  userX=1;
                  userXRelative=(RELATIVE_WIDTH_OF_WALL+1.0)/2.0;
                  userY=1;
                  userYRelative=(RELATIVE_WIDTH_OF_WALL+1.0)/2.0;
                  userPage[userY][userX]='\001';
                  clearUserAttempts=false;
                }
              if (paint)
                {
                  cornerX=new int [4];
                  cornerY=new int [4];
                  cornerX[0]=0;
                  cornerY[0]=0;
                  cornerX[1]=0;
                  cornerY[1]=screen.height;
                  cornerX[2]=screen.width;
                  cornerY[2]=screen.height;
                  cornerX[3]=screen.width;
                  cornerY[3]=0;
                  graph.setColor(new Color(255,255,255));
                  graph.fillPolygon(cornerX,cornerY,4);
                  radiansPerDegree=Math.atan(1.0)/45.0;
                  radians=tilt*radiansPerDegree;
                  sinTilt=Math.sin(radians);
                  cosTilt=Math.cos(radians);
                  temDouble1=(double) numColumns;
                  xMax=temDouble1+RELATIVE_WIDTH_OF_WALL;
                  temDouble1=(double) (screen.width);
                  pixelsPerX=(temDouble1-1.0)
                   /(xMax*(xMax/(xMax-RELATIVE_HEIGHT_OF_WALL)));
                  xOffset=(xMax/2.0)
                   *(RELATIVE_HEIGHT_OF_WALL/(xMax-RELATIVE_HEIGHT_OF_WALL));
                  temDouble1=(double) numRows;
                  yMax=temDouble1+RELATIVE_WIDTH_OF_WALL;
                  temDouble1=(double) (screen.height);
                  pixelsPerZ=(temDouble1-1.0)/Math.sqrt(yMax*yMax
                   +RELATIVE_HEIGHT_OF_WALL*RELATIVE_HEIGHT_OF_WALL);
                  if (yMax > xMax)
                    relDistOfUserFromScreen=yMax;
                  else
                    relDistOfUserFromScreen=xMax;
                  paint=false;
                }
              if (state == 0)
                state=1;
              break;
            case 1:
              baseRectangle[0][0].x=0.0;
              baseRectangle[0][0].y=0.0;
              baseRectangle[0][1].x=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[0][1].y=0.0;
              baseRectangle[0][2].x=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[0][2].y=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[0][3].x=0.0;
              baseRectangle[0][3].y=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[1][0].x=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[1][0].y=0.0;
              baseRectangle[1][1].x=1.0;
              baseRectangle[1][1].y=0.0;
              baseRectangle[1][2].x=1.0;
              baseRectangle[1][2].y=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[1][3].x=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[1][3].y=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[2][0].x=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[2][0].y=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[2][1].x=1.0;
              baseRectangle[2][1].y=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[2][2].x=1.0;
              baseRectangle[2][2].y=1.0;
              baseRectangle[2][3].x=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[2][3].y=1.0;
              baseRectangle[3][0].x=0.0;
              baseRectangle[3][0].y=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[3][1].x=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[3][1].y=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[3][2].x=RELATIVE_WIDTH_OF_WALL;
              baseRectangle[3][2].y=1.0;
              baseRectangle[3][3].x=0.0;
              baseRectangle[3][3].y=1.0;
              rectangle[0][0].x=0.0;
              rectangle[0][0].y=0.0;
              rectangle[0][1].x=xMax;
              rectangle[0][1].y=0.0;
              rectangle[0][2].x=xMax;
              rectangle[0][2].y=yMax;
              rectangle[0][3].x=0.0;
              rectangle[0][3].y=yMax;
              x0=rectangle[0][0].x;
              y0=rectangle[0][0].y;
              x1=rectangle[0][1].x;
              y1=rectangle[0][1].y;
              x2=rectangle[0][2].x;
              y2=rectangle[0][2].y;
              x3=rectangle[0][3].x;
              y3=rectangle[0][3].y;
              displayQuadrilateral(x0,y0,0.0,x1,y1,0.0,x2,y2,0.0,x3,y3,0.0,
               FLOOR_COLOR);
              y=0;
              yOffset=0;
              state=4;
              break;
            case 4:
              if (y <= maxY)
                {
                  for (vertexNum=0; vertexNum < 4; ++vertexNum)
                    {
                      rectangle[0][vertexNum].x=baseRectangle[0][vertexNum].x;
                      rectangle[0][vertexNum].y
                       =baseRectangle[0][vertexNum].y+yOffset;
                    }
                  x=0;
                  while (x <= maxX)
                    {
                      if (computerPage[y][x] == '\000')
                        {
                          singleRectangle[0].x=rectangle[0][0].x;
                          singleRectangle[0].y=rectangle[0][0].y;
                          singleRectangle[1].x=rectangle[0][1].x;
                          singleRectangle[1].y=rectangle[0][1].y;
                          singleRectangle[2].x=rectangle[0][2].x;
                          singleRectangle[2].y=rectangle[0][2].y;
                          singleRectangle[3].x=rectangle[0][3].x;
                          singleRectangle[3].y=rectangle[0][3].y;
                          outputLeftRight(singleRectangle);
                        }
                      for (vertexNum=0; vertexNum < 4; ++vertexNum)
                        ++rectangle[0][vertexNum].x;
                      x+=2;
                    }
                  for (vertexNum=0; vertexNum < 4; ++vertexNum)
                    {
                      rectangle[0][vertexNum].x=baseRectangle[0][vertexNum].x;
                      rectangle[0][vertexNum].y
                       =baseRectangle[0][vertexNum].y+yOffset;
                    }
                  for (vertexNum=0; vertexNum < 4; ++vertexNum)
                    {
                      rectangle[1][vertexNum].x=baseRectangle[1][vertexNum].x;
                      rectangle[1][vertexNum].y
                       =baseRectangle[1][vertexNum].y+yOffset;
                    }
                  x=0;
                  while (x <= maxX)
                    {
                      if (computerPage[y][x] == '\000')
                        {
                          singleRectangle[0].x=rectangle[0][0].x;
                          singleRectangle[0].y=rectangle[0][0].y;
                          singleRectangle[1].x=rectangle[0][1].x;
                          singleRectangle[1].y=rectangle[0][1].y;
                          singleRectangle[2].x=rectangle[0][2].x;
                          singleRectangle[2].y=rectangle[0][2].y;
                          singleRectangle[3].x=rectangle[0][3].x;
                          singleRectangle[3].y=rectangle[0][3].y;
                          outputRectangle(singleRectangle,
                           RECTANGLE_W_E_COLOR);
                        }
                      for (vertexNum=0; vertexNum < 4; ++vertexNum)
                        ++rectangle[0][vertexNum].x;
                      ++x;
                      if (x <= maxX)
                        {
                          if (computerPage[y][x] == '\000')
                            {
                              singleRectangle[0].x=rectangle[1][0].x;
                              singleRectangle[0].y=rectangle[1][0].y;
                              singleRectangle[1].x=rectangle[1][1].x;
                              singleRectangle[1].y=rectangle[1][1].y;
                              singleRectangle[2].x=rectangle[1][2].x;
                              singleRectangle[2].y=rectangle[1][2].y;
                              singleRectangle[3].x=rectangle[1][3].x;
                              singleRectangle[3].y=rectangle[1][3].y;
                              outputRectangle(singleRectangle,
                               RECTANGLE_W_E_COLOR);
                            }
                          for (vertexNum=0; vertexNum < 4; ++vertexNum)
                            ++rectangle[1][vertexNum].x;
                          ++x;
                        }
                    }
                  ++y;
                  if (y <= maxY)
                    {
                      for (vertexNum=0; vertexNum < 4; ++vertexNum)
                        {
                          rectangle[3][vertexNum].x=baseRectangle[3][vertexNum].x;
                          rectangle[3][vertexNum].y
                           =baseRectangle[3][vertexNum].y+yOffset;
                        }
                      x=0;
                      while (x <= maxX)
                        {
                          if (computerPage[y][x] == '\000')
                            {
                              singleRectangle[0].x=rectangle[3][0].x;
                              singleRectangle[0].y=rectangle[3][0].y;
                              singleRectangle[1].x=rectangle[3][1].x;
                              singleRectangle[1].y=rectangle[3][1].y;
                              singleRectangle[2].x=rectangle[3][2].x;
                              singleRectangle[2].y=rectangle[3][2].y;
                              singleRectangle[3].x=rectangle[3][3].x;
                              singleRectangle[3].y=rectangle[3][3].y;
                              outputLeftRight(singleRectangle);
                            }
                          for (vertexNum=0; vertexNum < 4; ++vertexNum)
                            ++rectangle[3][vertexNum].x;
                          x+=2;
                        }
                      for (vertexNum=0; vertexNum < 4; ++vertexNum)
                        {
                          rectangle[3][vertexNum].x=baseRectangle[3][vertexNum].x;
                          rectangle[3][vertexNum].y
                           =baseRectangle[3][vertexNum].y+yOffset;
                        }
                      x=0;
                      while (x <= maxX)
                        {
                          if (computerPage[y][x] == '\000')
                            {
                              singleRectangle[0].x=rectangle[3][0].x;
                              singleRectangle[0].y=rectangle[3][0].y;
                              singleRectangle[1].x=rectangle[3][1].x;
                              singleRectangle[1].y=rectangle[3][1].y;
                              singleRectangle[2].x=rectangle[3][2].x;
                              singleRectangle[2].y=rectangle[3][2].y;
                              singleRectangle[3].x=rectangle[3][3].x;
                              singleRectangle[3].y=rectangle[3][3].y;
                              outputRectangle(singleRectangle,
                               RECTANGLE_W_E_COLOR);
                            }
                          for (vertexNum=0; vertexNum < 4; ++vertexNum)
                            ++rectangle[3][vertexNum].x;
                          x+=2;
                        }
                      ++y;
                    }
                  ++yOffset;
                }
              else
                state=5;
              if (state == 5)
                {
                  alreadyPainting=false;
                  sqrDisplayUserMoves();
                  if (solutionDisplayed)
                    sqrDisplaySolution();
                }
              break;
            default:
              break;
          }
        return;
      }

    private synchronized boolean sizeChanged(
      boolean value)
        {
          boolean result=resize;

          resize=value;
          return result;
        }

    public synchronized boolean startOver(
      boolean value)
        {
          boolean result=restart;

          restart=value;
          if (restart)
            {
              if (mazeCanvas.resize)
                sizeChanged(true);
            }
          return result;
        }

    public void run()
      {
        do
          {
            if (state < 5)
              {
                if (hexagonalRooms)
                  hexOutputMaze();
                else
                  sqrOutputMaze();
              }
            yield();
            if (startOver(false))
              {
                graph=mazeCanvas.getGraphics().create();
                screen=new Rectangle(mazeCanvas.rectangle.x,
                 mazeCanvas.rectangle.y,mazeCanvas.rectangle.width,
                 mazeCanvas.rectangle.height);
                if (resize)
                  userHasSolved=false;
                hexagonalRooms=mazeCanvas.maze3D.hexagonalRooms;
                solutionDisplayed=mazeCanvas.maze3D.solutionDisplayed;
                if (mazeCanvas.maze3D.clearUserAttempts)
                  {
                    clearUserAttempts=true;
                    mazeCanvas.maze3D.clearUserAttempts=false;
                    userHasSolved=false;
                  }
                tilt=mazeCanvas.maze3D.tilt;
                paint=true;
                alreadyPainting=true;
                state=0;
              }
          }
        while (state < 6);
      }
  }
