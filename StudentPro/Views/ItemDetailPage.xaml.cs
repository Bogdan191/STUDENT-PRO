using StudentPro.ViewModels;
using System.ComponentModel;
using Xamarin.Forms;

namespace StudentPro.Views
{
    public partial class ItemDetailPage : ContentPage
    {
        public ItemDetailPage()
        {
            InitializeComponent();
            BindingContext = new ItemDetailViewModel();
        }
    }
}