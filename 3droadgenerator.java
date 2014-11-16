package javaapplication9;

/**
 *
 * @author anishkanchan
 */

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
public class RoadRashTest extends Frame implements Runnable,MouseListener,KeyListener
{       static int left=0,right=0;
        static int carx=200,cary=150;
	static int ctrlx=200,ctrly=80,x=220,y=50;                               //x is the variable endpoint x coordinate 	
	static int py=40;							//  ctrlx is the middle control point x coordinate
	static boolean flag=true;						//ctrly is the middle control point y coordinate
	static int speedhelpup=0,speedhelpdown=0,speed=0;
        static BufferedImage image;                                             //  pctrly is the middle control point y coordinate
	static boolean flagleft=false;						//  py is the variable endpoint y coordinate
	static int rctrlx=270,rx=250,rctrly=50;                                 //int rctrlx=250,rctrly=80,rx=330,ry=50,rpy=40,rpctrly=50;
	static 	int arrx[]=new int[15];
        static 	int arry[]=new int[15];
        static	int ary[]=new int[15];
        static	int rarrx[]=new int[15];
        static QuadCurve2D a,b,c;
	public static void main(String args[])
	{	Thread t;
                image = null;
                try {
                   image = ImageIO.read(new File("rowdy.jpg"));
                } catch (Exception  e) {
                }

                RoadRashTest ab=new RoadRashTest();
		ab.setSize(600,200);
		ab.addKeyListener(ab);
		ab.setVisible(true);
		ab.addMouseListener(ab);
                b=new QuadCurve2D.Double(100,90,ctrlx,rctrly,x,py);			
		a=new QuadCurve2D.Double(100,190,ctrlx,ctrly,x,y);				
		c=new QuadCurve2D.Double(370,190,rctrlx,ctrly,rx,y);
 //             ab.straight();
 //             ab.left();
                ab.right();
		System.exit(0);
	}
    @Override
        public void run()
        {
        
        }
        
        public void straight()
        {
                flag=true;
		int oy;								// local variable to find the y coordinates of 10 points for both curves one at a time
		int ox;								// local variable to find the x coordinates of 10 points for both curves one at a time
		
		int rox;		
		int check=0;							// holds the value of arrx[1] for checking purpose
                                                                                //here it is initialised temporarily..proper initialisation will be done later
		int polx;	
	
		int test=0;
		polx=ox=x-1;
                
									// outermost loop to control the curve
		
                        
			if(test==1)
			polx=ox=(x-1);
			b=new QuadCurve2D.Double(100,90,ctrlx,rctrly,x,py);			
			a=new QuadCurve2D.Double(100,190,ctrlx,ctrly,x,y);				
			c=new QuadCurve2D.Double(370,190,rctrlx,ctrly,rx,y);		
			
			test=0;						//to check how many times arrx[0] became arrx[1]
		
			
	here:		for(int cp=0;flag;cp++)					//first time values will get stored in the array and no comparisons will be made the this time	
			{							//value of cp indicates whether loop is run for first time
										//this loop causes the 10 points in both curves to move					
										//controls the no. of times lines must move before the curve changes
				
				for(int i=0;i<15;i++)				//produces 15 points on the top and bottom curves
				{
					
					if(i==0)
						oy=y;
					else
						oy=arry[i-1];
					for(;oy<=190;oy++)                      //for particular value of x finds corresponding value of y for bottom curve
					{
						
						if(ox<100)
						{
							arrx[i]=arry[i]=0;
							break;
						}
						if(a.intersects(ox,oy,1,1))
						{
							arrx[i]=ox;
							arry[i]=oy;
							break ;
						}
					}
					if(i==0)
						oy=py;
					else
						oy=ary[i-1];
					for(;oy<=90;oy++)									//for particular value of x finds corresponding value of y for top curve
					{
						if(ox<100)
						{
							arrx[i]=ary[i]=0;
						
							break;
						}
						if(b.intersects(ox,oy,1,1))
						{
							ary[i]=oy;
							break;
						}
					}
					
					for(rox=215;rox<=435;rox++)
					{
						if(c.intersects(rox,arry[i],1,1))
						{
							rarrx[i]=rox;
							break;
						}
					}
					double p=Math.hypot(x-arrx[i],y-arry[i]);//decreases at a rate proportional to distance between variable endpoint and previous point
					ox-=p/5+5;
				}
				if(cp==0)	
				{	
					check=arrx[1];
					
				}
				try{
			
					repaint();
					Thread.sleep(100-speed);
				}
				catch(Exception e)
				{
				}
				polx-=1;
				if(arrx[0]==check)
				{	
					polx=x-1;
					test+=1;
					if(test==50)
						break here;	
				}
				ox=polx;
			}
			                                                 
		}

        public void right()
	{
                flag=true;
		int oy;								// local variable to find the y coordinates of 10 points for both curves one at a time
		int ox;								// local variable to find the x coordinates of 10 points for both curves one at a time
		int roy;
		int rox;
		int check=0;							// holds the value of arrx[1] for checking purpose
                                                                                //here it is initialised temporarily..proper initialisation will be done later
		int polx;	
		
		int test=0;
		polx=ox=x-1;
                int ping=0,pong=0;
here:           while(flag)								// outermost loop to control the curve
		{
                   //     if((x>=320&right==1)||x<320)
                        {
			if(test==1)
			polx=ox=(x-1);
			b=new QuadCurve2D.Double(100,90,ctrlx,rctrly,x,py);			
			a=new QuadCurve2D.Double(100,190,ctrlx,ctrly,x,y);				
			c=new QuadCurve2D.Double(370,190,rctrlx,ctrly,rx,y);		
			if(x<400)
			test=0;						//to check how many times arrx[0] became arrx[1]
		
			
			for(int cp=0;flag;cp++)					//first time values will get stored in the array and no comparisons will be made the this time	
			{							//value of cp indicates whether loop is run for first time
										//this loop causes the 10 points in both curves to move					
										//controls the no. of times lines must move before the curve changes
				
				for(int i=0;i<15;i++)				//produces 15 points on the top and bottom curves
				{
					
					if(i==0)
						oy=y;
					else
						oy=arry[i-1];
					for(;oy<=190;oy++)                      //for particular value of x finds corresponding value of y for bottom curve
					{
						
						if(ox<100)
						{
							arrx[i]=arry[i]=0;
							break;
						}
						if(a.intersects(ox,oy,1,1))
						{
							arrx[i]=ox;
							arry[i]=oy;
							break ;
						}
					}
					if(i==0)
						oy=py;
					else
						oy=ary[i-1];
					for(;oy<=90;oy++)									//for particular value of x finds corresponding value of y for top curve
					{
						if(ox<100)
						{
							arrx[i]=ary[i]=0;
						
							break;
						}
						if(b.intersects(ox,oy,1,1))
						{
							ary[i]=oy;
							break;
						}
					}
					
					for(rox=215;rox<=435;rox++)
					{
						if(c.intersects(rox,arry[i],1,1))
						{
							rarrx[i]=rox;
							break;
						}
					}
					double p=Math.hypot(x-arrx[i],y-arry[i]);//decreases at a rate proportional to distance between variable endpoint and previous point
					ox-=p/5+5;
				}
				if(cp==0)	
				{	
					check=arrx[1];
				}
				try{
					repaint();
					Thread.sleep(100-speed);
				}
				catch(Exception e)
				{
				}
				polx-=1;
				if(arrx[0]==check)
				{	
					polx=x-1;
					test+=1;
					if(test==1&&(x+1<=400||rx+1<=430))
						break;		
							
					if(test==20)
						break here;	
				}
				ox=polx;
			}
			if(x+1<=400)
                        {x+=1;							// while loop condition makes sure that
			ping+=1;							// control reaches here only if x>=120
			pong+=1;}
                        if(rx+1<=430)
                        {rx+=1;
                        }
                       
			if(rctrlx+1<=350&ping==2)
                        {rctrlx+=1;
                        ping=0;
                        }										
  			if(ctrlx+1<=200&pong==2)					// once the curve becomes a line then x
                        {ctrlx+=1;
                            pong=0;
                        }                                                               // must remain constant
		}
		
                right=0;
            }
            flag=true;
			
 here:          while(flag)							// outermost loop to control the curve
		{
                //        if((x>=320&right==1)||x<320)
                        {
			polx=ox=x-1;
			b=new QuadCurve2D.Double(100,90,ctrlx,rctrly,x,py);			
			a=new QuadCurve2D.Double(100,190,ctrlx,ctrly,x,y);				
			c=new QuadCurve2D.Double(370,190,rctrlx,ctrly,rx,y);		
			test=0;						//to check how many times arrx[0] became arrx[1]
	
			
			for(int cp=0;flag;cp++)					//first time values will get stored in the array and no comparisons will be made the this time	
			{
				int r=1;					//value of cp indicates whether loop is run for first time
										//this loop causes the 10 points in both curves to move					
										//controls the no. of times lines must move before the curve changes
				
				for(int i=0;i<15;i++)				//produces 15 points on the top and bottom curves
				{
					
					if(i==0)
						oy=y;
					else
						oy=arry[i-1];
					for(;oy<=190;oy++)			//for particular value of x finds corresponding value of y for bottom curve
					{
						
						if(ox<100)
						{
							arrx[i]=arry[i]=0;
							break;
						}
						if(a.intersects(ox,oy,1,1))
						{
							arrx[i]=ox;
							arry[i]=oy;
							break ;
						}
					}
					if(i==0)
						oy=py;
					else
						oy=ary[i-1];
					for(;oy<=90;oy++)			//for particular value of x finds corresponding value of y for top curve
					{
						if(ox<100)
						{
							arrx[i]=ary[i]=0;
						
							break;
						}
						if(b.intersects(ox,oy,1,1))
						{
							ary[i]=oy;
							break;
						}
					}
					
					for(rox=115;rox<=435;rox++)
					{
						if(c.intersects(rox,arry[i],1,1))
						{
							rarrx[i]=rox;
							break;
						}
					}
					double p=Math.hypot(x-arrx[i],y-arry[i]);//decreases at a rate proportional to distance between variable endpoint and previous point
					ox-=p/5+5;
				}
				if(cp==0)	
				{	
					check=arrx[1];
					
				}
				try{
			
					repaint();
					Thread.sleep(100-speed);
				}
				catch(Exception e)
				{
				}
				polx-=1;
				if(arrx[0]==check)
				{	
					polx=x-1;
					test+=1;
					if(test==1&&(x-3>=220||rx-2>=250))
						break;		
                                        if(test==10)
                                            break here;
				}
				ox=polx;
			}
			if(x-3>=220)
   			x-=3;							// while loop condition makes sure that
										// control reaches here only if x>=120
			if(rx-2>=250)
			rx-=3;
			if(rctrlx-1>=270)
			rctrlx-=2;										
  			if(ctrlx-1>199)						// once the curve becomes a line then x
   			ctrlx-=1;						// must remain constant
		}
             }
	}
    @Override
	public void mouseReleased(MouseEvent e)
	{
	}
    @Override
	public void mousePressed(MouseEvent e)
	{
	}
    @Override

    public void	mouseExited(MouseEvent e)
	{
	}
    @Override
	public void	mouseEntered(MouseEvent e)
	{
	}
    @Override
	public void	mouseClicked(MouseEvent e)
	{
	//	flag=false;
	//	System.exit(0);
	}
	
    @Override
	public void paint(Graphics g)
	{	
		for(int i=0;i<15;i++)
		{		
			g.drawLine(rarrx[i],arry[i],rarrx[i],ary[i]);
			g.drawLine(arrx[i],arry[i],arrx[i],ary[i]);
		}
        	g.drawLine(0,50,500,50);
		Graphics2D g1=(Graphics2D)g;
		g1.draw(a);
		g1.draw(c);
                g.drawImage(image,carx,cary,null);

	}
    
    
    
    
    
    	public void left()
	{
	
										// local variable to find the y coordinates of 10 points for both curves one at a time
		int ox;								// local variable to find the x coordinates of 10 points for both curves one at a time
		int roy;
		int rox;       
                int check=0;							// holds the value of arrx[1] for checking purpose
                                                                                //here it is initialised temporarily..proper initialisation will be done later
		int polx;
                QuadCurve2D.Double d;
		int test=0;
                int ping=0,pong=0;
    here:	while(flag)								// outermost loop to control the curve
                    {    
		//	if((x<=150&left==1)||x>150)
                        {
                        
			rox=rx+1;
                        polx=rx+1;
			b=new QuadCurve2D.Double(100,90,ctrlx,rctrly,x,py);			
			a=new QuadCurve2D.Double(100,190,ctrlx,ctrly,x,y);				
			c=new QuadCurve2D.Double(370,190,rctrlx,ctrly,rx,y);	
                        d=new QuadCurve2D.Double(370, 90, rctrlx,rctrly, rx, py);
			
                        if(x>40)
			test=0;						//to check how many times arrx[0] became arrx[1]
		
			
			for(int cp=0;flag;cp++)					//first time values will get stored in the array and no comparisons will be made the this time	
			{							//value of cp indicates whether loop is run for first time
										//this loop causes the 10 points in both curves to move					
										//controls the no. of times lines must move before the curve changes
				
				for(int i=0;i<15;i++)				//produces 15 points on the top and bottom curves
				{
					
					if(i==0)
						roy=y;
					else
						roy=arry[i-1];
					for(;roy<=190;roy++)                      //for particular value of x finds corresponding value of y for bottom curve
					{
						
						if(rox>370)
						{
							rarrx[i]=arry[i]=0;
							break;
						}
						if(c.intersects(rox,roy,1,1))
						{
							rarrx[i]=rox;
							arry[i]=roy;
							break ;
						}
					}
					if(i==0)
						roy=py;
					else
						roy=ary[i-1];
					for(;roy<=90;roy++)									//for particular value of x finds corresponding value of y for top curve
					{
						if(rox>370)
						{
							rarrx[i]=ary[i]=0;
						
							break;
						}
						if(d.intersects(rox,roy,1,1))
						{
							ary[i]=roy;
							break;
						}
					}
					
					for(ox=rarrx[i];ox>=40;ox--)
					{
						if(a.intersects(ox,arry[i],1,1))
						{
							arrx[i]=ox;
							break;
						}
					}
					double p=Math.hypot(rx-rarrx[i],y-arry[i]);//decreases at a rate proportional to distance between variable endpoint and previous point
					rox+=p/5+5;
				}
				if(cp==0)	
				{	
					check=rarrx[1];
					
				}
				try{
					repaint();
					Thread.sleep(100-speed);
				}
				catch(Exception e)
				{
				}
				polx+=1;
				if(rarrx[0]==check)
				{	
					polx=rx+1;
					test+=1;
					if(test==1&&(x-1>40||rx-1>70))
						break;		
                               		if(test==10)
						break here;	
				}
				rox=polx;
			}
                        
			if(x-1>40)
                        {
                            x-=1;							// while loop condition makes sure that
                            ping+=1;							// control reaches here only if x>=120
                            pong+=1;
                        }
                        if(rx-1>70)
                        {
                            rx-=1;
                        }
                       
			if(rctrlx-1>200&ping==3)
                        {
                            rctrlx-=2;
                            ping=0;
                        }										
  			if(ctrlx-2>64&pong==3)					// once the curve becomes a line then x
                        {
                            ctrlx-=2;
                            pong=0;
                        }                                                           // must remain constant
                        
                        left=0;
		}

                        
		
}
		flag=true;
		
		ping=0;pong=0;
			
  here:         while(flag)							// outermost loop to control the curve
		{
                  //      if((x<=150&left==1)||x>150)
                        {   
			polx=rox=rx+1;
			b=new QuadCurve2D.Double(100,90,ctrlx,rctrly,x,py);			
			a=new QuadCurve2D.Double(100,190,ctrlx,ctrly,x,y);				
			c=new QuadCurve2D.Double(370,190,rctrlx,ctrly,rx,y);	
                        d=new QuadCurve2D.Double(370, 90, rctrlx,rctrly, rx, py);	
                        
			if(x<220)
                        test=0;						//to check how many times arrx[0] became arrx[1]
	
			for(int cp=0;flag;cp++)					//first time values will get stored in the array and no comparisons will be made the this time	
			{
                                                                            	//value of cp indicates whether loop is run for first time
										//this loop causes the 10 points in both curves to move					
										//controls the no. of times lines must move before the curve changes
				
				for(int i=0;i<15;i++)				//produces 15 points on the top and bottom curves
				{
					
					if(i==0)
						roy=py;
					else
						roy=arry[i-1];
					for(;roy<=190;roy++)			//for particular value of x finds corresponding value of y for bottom curve
					{
						
						if(rox>370)
						{
							arrx[i]=arry[i]=0;
							break;
						}
						if(c.intersects(rox,roy,1,1))
						{
							rarrx[i]=rox;
							arry[i]=roy;
							break ;
						}
					}
					if(i==0)
						roy=py;
					else
						roy=ary[i-1];
					for(;roy<=90;roy++)			//for particular value of x finds corresponding value of y for top curve
					{
						if(rox>370)
						{
							arrx[i]=ary[i]=0;
						
							break;
						}
						if(d.intersects(rox,roy,1,1))
						{
							ary[i]=roy;
							break;
						}
					}
					
					for(ox=370;ox>=40;ox--)
					{
						if(a.intersects(ox,arry[i],1,1))
						{
							arrx[i]=ox;
							break;
						}
					}
					double p=Math.hypot(rx-rarrx[i],y-arry[i]);//decreases at a rate proportional to distance between variable endpoint and previous point
					rox+=p/5+5;
				}
				if(cp==0)	
				{	
					check=rarrx[1];
					
				}
				try{
			
					repaint();
					Thread.sleep(100-speed);
				}
				catch(Exception e)
				{
				}
				polx+=1;
				if(rarrx[0]==check)
				{	
					polx=rx+1;
					test+=1;
					if(test==1&&(x+1<220||rx+1<250))
						break;
                                        if(test==10)
                                            break here;
				}
				rox=polx;
			}
			if(x+1<220)
                        {
                            x+=1;							// while loop condition makes sure that
                            ping+=1;						// control reaches here only if x>=120
                            pong+=1;
                        }
                        if(rx+1<250)
                        {rx+=1;
                        }
                       
			if(rctrlx+1<270&ping==3)
                        {rctrlx+=2;
                        ping=0;
                        }										
  			if(ctrlx+1<200&pong==3)					// once the curve becomes a line then x
                        {ctrlx+=2;
                            pong=0;
                        }    						// must remain constant
		}
                        left=0;
            }
		
    
             
        }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP)
    	{
    		speedhelpup+=1;	
    	}
    	else if(e.getKeyCode()==KeyEvent.VK_DOWN)
    	{
            speedhelpdown+=1;
    	}
        if(speedhelpup==25&speed<70)
        {
            speed+=(2+10*speed/(speed+1));
            System.out.println("speed"+speed);
            speedhelpup=0;
            speedhelpdown=0;
        }
        if(speedhelpdown==25&speed-(2+10*speed/(speed+1))>0)
        {
            speed-=(2+10*speed/(speed+1));
            System.out.println("speed"+speed);
            speedhelpup=0;
            speedhelpdown=0;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
    	{        if(x<320)
                 {   if(carx+5<=250)
                        carx+=5;
                 }
                 else
                {
                    right=1;
                }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
    	{   
            if(x>150)
            {  if(carx-5>=110)
    		carx-=5;
            }
            else 
                { left=1;
                }
         
            
    	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}