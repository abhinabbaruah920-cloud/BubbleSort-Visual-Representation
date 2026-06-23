import java.awt.*;
import java.util.*;
import javax.swing.*;

class BSort{
    static class Data extends JFrame{
        JTextArea area;
        Data(){
            area=new JTextArea();
            setTitle("DATA");
            setSize(300,300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            area.setEditable(false);
            add(new JScrollPane(area));
            setVisible(true);
        }

        void updateData(int[] arr){
            area.append(Arrays.toString(arr)+"\n");
        }
    }
    static class Bar extends JPanel{
        int[] arr;
        int c1=-1,c2=-1;
        int s1=-1,s2=-1;
        Bar(int[] arr){
            this.arr=arr;
        }

        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            int width= getWidth();
            int height=getHeight();
            int barwidth=width/arr.length;
            
            for(int i=0;i<arr.length;i++){
                if(i==s1 || i==s2)
                    g.setColor(Color.ORANGE);
                else if(i==c1 || i==c2)
                    g.setColor(Color.GREEN);
                else
                    g.setColor(Color.BLUE);
                
                int barheight=arr[i]*2;

                g.fillRect(i*barwidth,height-barheight,barwidth-1,barheight);
            }
        }
    }
    
    static class Barframe extends JFrame{
        Bar b;
        Barframe(int[] arr){
            setTitle("Sorting Bars");
            setSize(700,700);
            b=new Bar(arr);
            add(b);
            setVisible(true);
        }
    }

    static class BubbleSort extends Thread {
        int[] arr;
        Data d;
        Bar b;

        BubbleSort(int[] arr,Data d,Bar b){
            this.arr=arr;
            this.d=d;
            this.b=b;
        }

        public void run(){
            d.updateData(arr);
            try{
                for(int i=0;i<arr.length-1;i++){
                for(int j=0;j<arr.length-i-1;j++){
                    b.c1=j;
                    b.c2=j+1;
                    b.s1=b.s2=-1;
                    b.repaint();
                    Thread.sleep(150);

                    if(arr[j]>arr[j+1]){
                        b.s1=j;
                        b.s2=j+1;
                        b.repaint();
                        Thread.sleep(150);
                        int temp=arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=temp;
                        b.repaint();
                        d.updateData(arr);
                        Thread.sleep(150);
                    }
                    }
                }
            b.c1=b.c2=-1;
            b.s1=b.s2=-1;
            b.repaint();
            }catch(InterruptedException e){
                System.out.println(e);
            }

        }
        
    }

    public static void main(String[] args){
        int[] arr={200,180,140,160,20,120,40,100,60,80};
        Data d= new Data();
        Barframe bf = new Barframe(arr);
        BubbleSort sort =new BubbleSort(arr,d,bf.b);
        sort.start();
    }
}

