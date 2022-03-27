public class Path {
    private String path;

    public Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void cd(String newPath) {
           
        String[] nou=newPath.split("/");
        String[] old=path.split("/");
        
        //numaram de cate ori apare ".." in noul path adica de cate ori tebuie sa urcam in ierarhia de directoare
        int parentcount=0;
        
        for(String str:nou){
            
            if(str.equals(".."))
                parentcount++;
        }
        
        //cream un nou string in care pastram directoarele parinte adica vechea cale - de cate ori urcam in ierarhie
        String strnou="";
        
        for(int i=0;i<old.length-parentcount;i++){
            strnou=strnou+old[i]+"/";
        }
        
        for(int i=0;i<nou.length;i++){
            if(!nou[i].equals(".."))
                strnou=strnou+nou[i]+"/";
        }
        
        path=strnou.substring(0, strnou.length()-1);
        System.out.println(path);
     
        //pentru ca nu avem o cale invalida singura exceptie poate fii ca noua cale sa urce prea mult in ierarhia de directoare astfel incat sa urce de root /
        if(path.indexOf("/")<0)
          throw new UnsupportedOperationException("Directory not found");
        
    }
    

    public static void main(String[] args) {
        Path path = new Path("/a/b/c/d");
        path.cd("../x");
        System.out.println(path.getPath());
    }
}