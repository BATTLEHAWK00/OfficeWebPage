<script>
import GlobalVar from "./Global";
export default {
  SetTitle(name) {
    if (arguments.length == 0) {
      document.title = GlobalVar.OfficeName;
      return;
    }
    document.title = name + " - " + GlobalVar.OfficeName;
  },
};
</script>