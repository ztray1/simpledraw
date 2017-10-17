package sketchboard;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DrawPad extends JFrame {

	private JPanel contentPane; // private DrawArea drawarea;
	// Container con = getContentPane()DrawArea;
	BufferedImage bi = new BufferedImage(120, 120, BufferedImage.TYPE_INT_ARGB);
	// Graphics2D g2d = bi.createGraphics();
	Graphics g2d = bi.createGraphics();
	int index = 0;
	int startx, starty, endx, endy, endx1, endy1, selectedstartx, selectedstarty, selectedendx, selectedendy;
	int flag;
	int a[] = new int[4];
	int returnValue;
	JFileChooser browser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "jpg");
	File selectedFile;
	int jrb;
	Component c1;
	int jcolor = 4;
	private String path = System.getProperty("user.dir");
	int h, w;
	File selectedfile, savedfile;
	BufferedImage image;
	private BufferedImage newImage;
	private int height, width;
	JPanel panel = new JPanel();
	Component c = panel;
	private int currentPixArray[] = null;
	// 图像的路径
	private String fileString = null;
	// 用于显示图像的标签
	private JLabel imageLabel = null;
	// 加载的图像

	public int min(int a, int b) {
		int c;
		if (a >= b) {
			c = b;
		} else {
			c = a;

		}
		return c;
	}

	public int max(int m, int n) {
		int l;
		if (m >= n) {
			l = m;
		} else {
			l = n;
		}
		return l;
	}

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawPad frame = new DrawPad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DrawPad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1166, 774);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("file");
		mnNewMenu.setFont(new Font("Tahoma", Font.BOLD, 15));
		menuBar.add(mnNewMenu);

		JMenuItem mntmOpenFile = new JMenuItem("open file");
		mntmOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("D:\\practice"));
				BufferedImage bi = new BufferedImage(h, w, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2d = bi.createGraphics();
				fs.setDialogTitle("Open a File");
				fs.setFileFilter(new FileNameExtensionFilter("PNG file", "png"));
				returnValue = fs.showOpenDialog(null);
				if (returnValue == fs.APPROVE_OPTION) {
					selectedFile = fs.getSelectedFile();
					System.out.println(selectedFile.toString());
					// image = new File(selectedFile);

					try {
						BufferedImage image = ImageIO.read(selectedFile);
						System.out.println(222222222);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		mnNewMenu.add(mntmOpenFile);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("exit");
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmSave = new JMenuItem("save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fs = new JFileChooser(new File("D:\\practice"));
				fs.setDialogTitle("Save a File");
				fs.setFileFilter(new FileNameExtensionFilter("PNG", ".png"));
				returnValue = fs.showSaveDialog(null);

				BufferedImage image = new BufferedImage(c1.getSize().width, c1.getSize().height,
						BufferedImage.TYPE_INT_ARGB);

				Graphics graphics = image.getGraphics();
				graphics.dispose();
				String path = fs.getSelectedFile().getPath();
				path += ".png";

				try {
					System.out.println(path);
					// System.out.println(dialog.getDirectory());

					ImageIO.write(image, "PNG", new File(path));
				} catch (IOException e1) {
					e1.printStackTrace();

				}
			}
		});
		mnNewMenu.add(mntmSave);

		JMenu mnNewMenu_1 = new JMenu("color");
		mnNewMenu_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("red");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcolor = 1;
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("green");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcolor = 2;
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("blue");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcolor = 3;
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("black");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcolor = 4;
			}
		});

		mnNewMenu_1.add(mntmNewMenuItem_7);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("yellow");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcolor = 5;
			}
		});

		mnNewMenu_1.add(mntmNewMenuItem_8);

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("pink");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcolor = 6;
			}
		});

		mnNewMenu_1.add(mntmNewMenuItem_9);

		JMenu mnNewMenu_2 = new JMenu("help");
		mnNewMenu_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmHelpTheme = new JMenuItem("help theme");
		mnNewMenu_2.add(mntmHelpTheme);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("version");
		mnNewMenu_2.add(mntmNewMenuItem_4);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("free hand");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jrb = 1;
			}
		});
		btnNewButton.setBounds(0, 0, 113, 27);
		contentPane.add(btnNewButton);

		JButton btnLine = new JButton("line");
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jrb = 2;
			}
		});
		btnLine.setBounds(0, 40, 113, 27);
		contentPane.add(btnLine);

		JButton btnRectangle = new JButton(" rectangle");
		btnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jrb = 3;
			}
		});
		btnRectangle.setBounds(0, 80, 113, 27);
		contentPane.add(btnRectangle);

		JButton btnNewButton_1 = new JButton("ellipses");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jrb = 4;
			}
		});
		btnNewButton_1.setBounds(0, 120, 113, 27);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("squares");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jrb = 5;
			}
		});
		btnNewButton_2.setBounds(0, 170, 113, 27);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton(" circles");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jrb = 6;
			}
		});
		btnNewButton_3.setBounds(0, 214, 113, 27);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("polygons ");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jrb = 7;
			}
		});
		btnNewButton_4.setBounds(0, 262, 113, 27);
		contentPane.add(btnNewButton_4);

		JPanel DrawPad = new JPanel();
		DrawPad.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				startx = e.getX();
				starty = e.getY();
				if (jrb == 7) {
					startx = endx;
					starty = endy;
				}
				if (jrb == 8) {
					a[0] = startx;
					a[1] = starty;
					System.out.println(a[0]);
					System.out.println(a[1]);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				Graphics g2d = DrawPad.getGraphics();
				((Graphics2D) g2d).setColor(new Color(144, 210, 41));
				// g2d.setColor(Color.BLUE);
				switch (jcolor) {
				case 1:
					g2d.setColor(Color.RED);
					break;
				case 2:
					g2d.setColor(Color.GREEN);
					break;
				case 3:
					g2d.setColor(Color.BLUE);
					break;
				case 4:
					g2d.setColor(Color.BLACK);
					break;
				case 5:
					g2d.setColor(Color.YELLOW);
					break;
				case 6:
					g2d.setColor(Color.PINK);
					break;

				}
				endx = e.getX();
				endy = e.getY();
				int c = endx - startx;
				int d = endy - starty;
				int max;
				if (c > d) {
					max = c;
				} else {
					max = d;
				}
				switch (jrb) {
				case 2:
					((Graphics2D) g2d).drawLine(startx, starty, endx, endy);
					DrawPad.getGraphics();
					System.out.println(startx);
					System.out.println(starty);
					index++;
					System.out.println(index);
					startx = endx;
					starty = endy;
					break;
				case 3:
					((Graphics2D) g2d).drawRect(min(startx, endx), min(starty, endy), Math.abs(c), Math.abs(d));
					index++;
					System.out.println(index);
					startx = endx;
					starty = endy;
					break;
				case 4:
					((Graphics2D) g2d).drawOval(min(startx, endx), min(starty, endy), Math.abs(c), Math.abs(d));
					index++;
					System.out.println(index);
					startx = endx;
					starty = endy;
					break;
				case 5:
					((Graphics2D) g2d).drawRect(min(startx, endx), min(starty, endy), max, max);
					index++;
					System.out.println(index);
					startx = endx;
					starty = endy;
					break;
				case 6:
					int dx = Math.abs(c);
					int dy = Math.abs(d);
					((Graphics2D) g2d).drawOval(min(startx, endx), min(starty, endy), max(dx, dy), max(dx, dy));
					index++;
					System.out.println(index);
					startx = endx;
					starty = endy;
					break;
				case 7:
					((Graphics2D) g2d).drawLine(startx, starty, endx, endy);
					index++;
					System.out.println(index);
					startx = endx;
					starty = endy;
					break;
				/*
				 * case 9: ((Graphics2D) g2d).copyArea(startx, starty, endx1,
				 * endy1, endx, endy); break;
				 */

				}
				if (jrb == 8) {
					a[2] = e.getX();
					a[3] = e.getY();
					System.out.println(a[0]);
					System.out.println(a[1]);

					startx = endx;
					starty = endy;

				}
				((Graphics2D) g2d).setColor(new Color(144, 242, 41));

			}

			private void prinfln(int startx) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				/*
				 * int mouseclickx = e.getX(); int mouseclicky = e.getY(); if
				 * (jrb == 9) { Graphics g2d = panel.getGraphics();
				 * ((Graphics2D) g2d).copyArea(a[0], a[1], a[2], a[3],
				 * mouseclickx - a[0], mouseclicky - a[1]);
				 * System.out.println(selectedstartx);
				 * System.out.println(selectedstarty);
				 * System.out.println(selectedendx);
				 * System.out.println(selectedendy);
				 * 
				 * }
				 */

			}
		});
		DrawPad.setBackground(Color.WHITE);
		// g2d.drawLine(startx,starty,endx,endy);
		// panel.paint(getGraphics());
		DrawPad.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int mouseclickx = e.getX();
				int mouseclicky = e.getY();
				if (jrb == 9) {
					Graphics g3d = DrawPad.getGraphics();

					((Graphics2D) g3d).copyArea(a[0], a[1], a[2], a[3], mouseclickx - a[0], mouseclicky - a[1]);
					System.out.println(selectedstartx);
					System.out.println(selectedstarty);
					System.out.println(selectedendx);
					System.out.println(selectedendy);

				}

				/*
				 * if (jrb == 1) { { Graphics g2d = panel.getGraphics(); switch
				 * (jcolor) { case 1: g2d.setColor(Color.RED); break; case 2:
				 * g2d.setColor(Color.GREEN); break; case 3:
				 * g2d.setColor(Color.BLUE); break; case 4:
				 * g2d.setColor(Color.BLACK); break; case 5:
				 * g2d.setColor(Color.YELLOW); break; case 6:
				 * g2d.setColor(Color.PINK); break; }
				 * 
				 * endx = e.getX(); endy = e.getY();
				 * 
				 * ((Graphics2D) g2d).drawLine(startx, starty, endx, endy);
				 * 
				 * startx = endx; starty = endy; }
				 */
				/*
				 * if (jrb == 8) { int selectedendx = e.getX(); int selectedendy
				 * = e.getY(); System.out.println(selectedendx);
				 * System.out.println(selectedendy); startx = endx; starty =
				 * endy;
				 * 
				 * }
				 */

			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});

		DrawPad.setBounds(127, 0, 1021, 701);
		contentPane.add(DrawPad);
		h = DrawPad.HEIGHT;
		w = DrawPad.WIDTH;
		c1 = DrawPad;
		GroupLayout gl_DrawPad = new GroupLayout(DrawPad);
		gl_DrawPad
				.setHorizontalGroup(gl_DrawPad.createParallelGroup(Alignment.LEADING).addGap(0, 1021, Short.MAX_VALUE));
		gl_DrawPad.setVerticalGroup(gl_DrawPad.createParallelGroup(Alignment.LEADING).addGap(0, 701, Short.MAX_VALUE));
		DrawPad.setLayout(gl_DrawPad);
		// System.out.println(h);
		System.out.println(w);
		JButton btnNewButton_5 = new JButton("save");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 * BufferedImage myImage = null; try { myImage = new Robot()
				 * .createScreenCapture(new Rectangle(237, 162,
				 * panel.getWidth(), panel.getHeight())); ImageIO.write(myImage,
				 * "png", new File("D:/practice/yuantu.png")); } catch
				 * (AWTException e2) { e2.printStackTrace(); } catch
				 * (IOException e1) { e1.printStackTrace(); }
				 */

				JFileChooser fs = new JFileChooser(new File("D:\\practice"));
				fs.setDialogTitle("Save a File");
				fs.setFileFilter(new FileNameExtensionFilter("PNG", ".png"));
				returnValue = fs.showSaveDialog(null);
				BufferedImage image = new BufferedImage(DrawPad.getSize().width, DrawPad.getSize().height,
						BufferedImage.TYPE_INT_ARGB);
				Graphics2D graphics = image.createGraphics();
				DrawPad.paint(graphics);
				graphics.dispose();
				// String path = fs.getSelectedFile().getPath();
				path += ".png";
				if (returnValue == fs.APPROVE_OPTION) {

					try {
						System.out.println(path);
						ImageIO.write(image, "PNG", new File(path));
					} catch (IOException e1) {
						e1.printStackTrace();

					}

					/*
					 * BufferedImage bi = (g2d.getWidth(), c.getHeight());
					 * c.paint(bi.getGraphics()); try { ImageIO.write(bi, "JPG",
					 * new File("D:/practice/yuantu.jpg")); } catch (IOException
					 * e1) { // TODO Auto-generated catch block
					 * e1.printStackTrace(); }
					 */

				}
			}
		});
		btnNewButton_5.setBounds(0, 313, 113, 27);
		contentPane.add(btnNewButton_5);

		JButton btnUndo = new JButton("selected");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jrb = 8;

			}
		});
		btnUndo.setBounds(0, 360, 113, 27);
		contentPane.add(btnUndo);

		JButton btnMove = new JButton("move");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jrb = 9;
			}
		});
		btnMove.setBounds(0, 417, 113, 27);
		contentPane.add(btnMove);

		JButton btnNewButton_7 = new JButton("delete");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Graphics gd = DrawPad.getGraphics();
				((Graphics2D) g2d).clearRect(a[0], a[1], a[2] - a[0], a[3] - a[1]);

			}
		});
		btnNewButton_7.setBounds(0, 470, 113, 27);
		contentPane.add(btnNewButton_7);
		Graphics image = DrawPad.getGraphics();
		setTitle("draw picture with mouse");
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
