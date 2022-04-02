package Tutorial;

    class Pattern
    {

        int A=0;
        int B=0;
        int C=0;
        
        //set volatile to the variable to make it accessible
        volatile boolean running=true;

        public void CountA()
        {
            while(running)
            A++;
        }

        public void CountB()
        {
            while(running)
            B++;
        }
        
        public void CountC()
        {
            while(running)
            C++;
        }

        public void Stop()
        {
            running = false;
        }

        public void Show()
        {
            System.out.print("\n\tA : " + A);
            System.out.print("\n\tB : " + B);
            System.out.print("\n\tC : " + C);
        }

    }

    class ThreadA extends Thread
    {
        Pattern P;

        ThreadA(Pattern P)
        {
        this.P = P;
        }

        public void run()
        {
        P.CountA();
        }
    }

    class ThreadB extends Thread
    {
        Pattern P;

        ThreadB(Pattern P)
        {
            this.P = P;
        }

        public void run()
        {
            P.CountB();
        }
    }

    class ThreadC extends Thread
    {
        Pattern P;

        ThreadC(Pattern P)
        {
            this.P = P;
        }

        public void run()
        {
            P.CountC();
        }
    }

    class Priority
    {
        public static void main(String arg[])
        {

            Pattern P = new Pattern();

            ThreadA t1 = new ThreadA(P);    //Statement 1
            ThreadB t2 = new ThreadB(P);    //Statement 2
            ThreadC t3 = new ThreadC(P);  

            t1.setPriority(Thread.MAX_PRIORITY); //10
            t2.setPriority(Thread.MIN_PRIORITY); //1
            t3.setPriority(Thread.NORM_PRIORITY); //5 -default
            
            t1.start();
            t2.start();
            t3.start();

            try
            {
                Thread.sleep(70);
            }
            catch (Exception e)
            {
                System.out.println("\n\tError.....");
            }


            P.Stop();
            P.Show();

        }
    }
