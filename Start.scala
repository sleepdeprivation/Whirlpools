import scala.collection.mutable.PriorityQueue
import scala.collection.mutable.Queue
import scala.collection.mutable.ListBuffer
import scala.io.Source._

object Start{

        object Direction extends Enumeration {
            type Direction = Value
            val mUp, mDown, mLeft, mRight, mNoMove = Value
        }

    class Cursor(){

        object Settings{
            var fairness:Boolean = true;
        }

        var location = new Pair[Int, Int](0, 0);

        var priority = Array(Direction.mRight, Direction.mDown, Direction.mLeft, Direction.mUp);

        /*
            Make d the last item in the priority list and move
            everything else up one
        */
        def dropToEnd(d:Direction.Value){
            for(q <- 0 until priority.length - 1){
                if(priority(q) == d){
                    priority(q) = priority(q+1)
                    priority(q+1) = d
                }
            }
        }

        def getMove(possible : List[Direction.Value]) : Direction.Value ={
            for(d <- priority){
                if(possible.contains(d)){
                    if(Settings.fairness) dropToEnd(d);
                    return d;
                }
            }
            return Direction.mNoMove;
        }

        def setLocation(x:Int, y:Int){
            location = new Pair[Int,Int](x, y)
        }

        def move(dir : Direction.Value): Pair[Int, Int] = {
            dir match{
                case Direction.mUp =>
                    setLocation(location._1, location._2 -1)

                case Direction.mLeft =>
                    setLocation(location._1 - 1, location._2)

                case Direction.mRight =>
                    setLocation(location._1 + 1, location._2)

                case Direction.mDown =>
                    setLocation(location._1, location._2 + 1)
            }
            return location;
        }
    }

    val r = scala.util.Random

    class Grid(x:Int , y:Int){

        var coords = Array.ofDim[Int](x, y);
        var xDim:Int = x;
        var yDim:Int = y;


        def readGrid(filename: String) : Array[Array[Int]] = {
          io.Source.fromFile(filename)
            .getLines()
            .map(_.toCharArray.map(Character.getNumericValue(_)))
            .toArray
        }
        def doNothing(filename:String){
/*            try{

                val lines = fromFile(filename).getLines
                xDim = Integer.parseInt(lines.next)
                yDim = Integer.parseInt(lines.next)
                var bufferCol : collection.mutable.ArrayBuffer[Array[Int]];
                var bufferRow : collection.mutable.ArrayBuffer[Int];
                var x: Int = 0;
                var y: Int = 0;
                for(line <- lines){
                    x = 0
                    for(char <- line){
                        bufferRow += Character.getNumericValue(char)
                        x+= 1;
                    }
                    bufferCol += bufferRow.toArray
                    bufferRow.trimEnd(xDim)
                    y+= 1;
                }
             }catch{
                    case file: java.io.FileNotFoundException =>
                        println(file);
             }*/
        }

        object Settings{
            var inertia:Boolean = true;
        }

        var cursor = new Cursor();
        
        var mapArrow : Array[String] = Array(".", "⇧", "⇦", "⇨", "⇩", "x");

        def printGrid(){
            //coords foreach { row => row foreach print; println }
            /*for( x <- coords ){
                    for( y <- x ){
                        print(mapArrow(y));
                    }
                println;
            }*/
            for (j <- 0 until xDim){
                println();
                for (i <- 0 until yDim){
                    print(mapArrow(coords(j)(i)))
                    print (" ");
                }
            }
            println();
        }
        
        def placeCursor(x:Int, y:Int){
            cursor.setLocation(x,y);
        }

        def get(x: Int, y: Int): Int = {
            if(x >= xDim || x < 0){
                return -1;
            }else if(y >= yDim || y < 0){
                return -1;
            }
            return coords(x)(y);
        }

        def checkPossible() : List[Direction.Value] = {
            var list = new ListBuffer[Direction.Value];
            var loc = cursor.location;
            var x = loc._1;
            var y = loc._2;
            if(get(x+1, y) == 0){
                list += Direction.mRight;
            }
            if(get(x-1, y) == 0){
                list += Direction.mLeft;
            }
            if(get(x, y+1) == 0){
                list += Direction.mDown;
            }
            if(get(x, y-1) == 0){
                list += Direction.mUp;
            }
            list += Direction.mNoMove;
            //println(list.toList);
            return list.toList;
        }

        //⇦ ⇨ ⇧ ⇩
        def mapNumber(dir : Direction.Value) : Int = {
            dir match{
                case Direction.mUp =>
                    return 2

                case Direction.mLeft =>
                    return 1

                case Direction.mRight =>
                    return 4

                case Direction.mDown =>
                    return 3
            }
            return 0;
        }

        def run(){
            placeCursor(0,0);
            var list = checkPossible();
            var nextMove = cursor.getMove(list);
            while(nextMove != Direction.mNoMove){
                Thread.sleep(100L);
                printGrid();
                coords(cursor.location._1)(cursor.location._2) = mapNumber(nextMove);
                cursor.move(nextMove);
                //coords(cursor.location._1)(cursor.location._2) = mapNumber(nextMove);
                //cursor.move(nextMove);

                list = checkPossible();
                if(!Settings.inertia || !list.contains(nextMove)){
                    nextMove = cursor.getMove(list);
                }
            }
            printGrid();
        }
    }

    /*
        An array of Ints with associated probability values,
        when get() is called it will return an element based on those probabilities
        WARNING: Almost certainly an off by 1 error in there.        
    */
    class ProbabilityArray(t: Array[Pair[Int, Int]]){
            var myArr = t;
            def getOne() : Int ={
                var index = r.nextInt(101); //precision up to the hundredths place
                var sum = 0;
                for(p <- myArr){
                    if(index <= sum + p._2){
                        return p._1;
                    }else{
                        sum += p._2;
                    }
                }
                return myArr(myArr.length-1)._1;
            }

    }


    /*


    */
    def probArrUnitTest(){
            //Providing this array will result in a 50/50 probability of 1 or 0.
            var coinFlip = Array[Pair[Int,Int]](
                Pair[Int, Int](1, 10),
                Pair[Int, Int](2, 10),
                Pair[Int, Int](3, 20),
                Pair[Int, Int](4, 30),
                Pair[Int, Int](5, 30)
            );
            var G = new ProbabilityArray(coinFlip)
            for(i <- 1 to 100000){
                println(G.getOne());
            }
    }

    class StateMachine(startState: Int, t: Array[ProbabilityArray]){
        var table = t;
        var currentState = startState;
        def transition() : Int = {
            currentState = table(currentState).getOne();
            return currentState;
        }
        def printMachine(){          
            //table foreach { row => row foreach print; println }
        }
    }

    def main(args: Array[String]){
        var g = new Grid(16, 16);
        g.coords = g.readGrid("./board")
        g.printGrid()
        g.run()
    }

}
