with Ada.Text_IO; use Ada.Text_IO;
with Geo; use Geo;
with Distance; use Distance;
with Util.Http.Clients;

procedure RoadNetworkEfficiency is    
    LatLng1 : constant LatLng := ( Lat => 25.044349, Lng => 55.214259);
    LatLng2 : constant LatLng := ( Lat => 25.044319, Lng => 55.213599);
    CityBlockDistance: Km;
begin
   CityBlockDistance := Distance.CityBlockDistance(LatLng1, LatLng2);
   
   Put(CityBlockDistance'Image); 
   Put(" km "); 
   New_Line(1);

end RoadNetworkEfficiency;

