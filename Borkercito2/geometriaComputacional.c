void midpointline(int x0, int y0, int x1, int y1){
  int dx=x1-x0;
  int dy=y1-y0;
  int d=2*dy-dx;
  int incyE=2*dy;
  int incyNE=2*(dy-dx)
  int x=x0;
  int y=y0;
  while(x<x1){
    if(d<=0){
      d+=incyNE;
      x++;
      y++;
    }
    else{
      d+=incyE;
      x++;
    }
  }
}
