//C# UDP Client

 

//Here is the USP Client for C#. Please note the commented line below. Without it it will not work.

 

        class WncUdpClient

        {

            UdpClient client;

            String ip = null;

            int port;

            Button b;

            TextBox t1, t2;

            SetTextCallback f;

 

            public WncUdpClient(ref Button bt, ref TextBox tb1, ref TextBox tb2, String rip, String rport, SetTextCallback ff)

            {

                client = new UdpClient();

 

                ip = rip;

                port = int.Parse(rport);

 

                b = bt;

                t1 = tb1;

                t2 = tb2;

                b.Click += tx;

 

                f = ff;

 

                Thread th = new Thread(rx);

                th.Start();

            }

 

            public void tx(Object sender, EventArgs e)

            {

                String toClient;

                toClient = t2.Text;

                if (toClient != null) client.Send(Encoding.ASCII.GetBytes(toClient), toClient.Length, ip, port); ;

            }

 

            void rx()

            {

                IPEndPoint RemoteIpEndPoint = new IPEndPoint(IPAddress.Any, 0);

                client.Client.Bind(RemoteIpEndPoint); // NOTE THIS LINE. THIS IS FOR THE UDP CLIENT ONLY

 

                String fromServer;

                do

                {

                    fromServer = Encoding.ASCII.GetString(client.Receive(ref RemoteIpEndPoint));

                    ip = RemoteIpEndPoint.Address + "";

                    port = RemoteIpEndPoint.Port;

                    if (fromServer != null) f(fromServer);

                }

                while (fromServer != null);

 

                System.Environment.Exit(0);

            }

        }